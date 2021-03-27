package sanchez.sergio.kmp_test.persistence.network.repository.core

import io.ktor.client.features.*
import io.ktor.client.statement.*
import io.ktor.utils.io.charsets.*
import io.ktor.utils.io.errors.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import sanchez.sergio.kmp_test.persistence.network.exception.NetworkBadRequestException
import sanchez.sergio.kmp_test.persistence.network.exception.NetworkErrorException
import sanchez.sergio.kmp_test.persistence.network.exception.NetworkException
import sanchez.sergio.kmp_test.persistence.network.exception.NetworkForbiddenException
import sanchez.sergio.kmp_test.persistence.network.exception.NetworkNoInternetException
import sanchez.sergio.kmp_test.persistence.network.exception.NetworkNoResultException
import sanchez.sergio.kmp_test.persistence.network.exception.NetworkUnauthorizedException
import sanchez.sergio.kmp_test.persistence.network.exception.NetworkUnverifiedAccountException

/**
 * Some HTTP response codes that We could get when making something request
 */
const val BAD_REQUEST_CODE: Int = 400
const val UNAUTHORIZED_CODE: Int = 401
const val NOT_FOUND_CODE: Int = 404
const val INTERNAL_SERVER_ERROR_CODE: Int = 500
const val CONFLICT_ERROR_CODE: Int = 409
const val FORBIDDEN_CODE: Int = 403

abstract class SupportNetworkRepository {

    /**
     * Wrap for safe Network Call
     * @param onExecuted
     */
    protected suspend fun <T> safeNetworkCall(onExecuted: suspend () -> T): T = withContext(
        Dispatchers.Default)  {
        try {
            onExecuted()
        } catch (exception: IOException){
            // map interrupted I/O to Network No Internet Exception
            throw NetworkNoInternetException()
        } catch (exception: NetworkException) {
            throw exception
        } catch (exception: ResponseException) {
            try {
                throw onApiException(exception)
            } catch (exception: Exception) {
                throw NetworkErrorException(cause = exception)
            }
        } catch (exception: Exception) {
            throw NetworkErrorException(cause = exception)
        }
    }

    /**
     * Map HTTP Error codes to exceptions to easy handler
     * @param responseException
     */
    open suspend fun onApiException(responseException: ResponseException): Exception =
        responseException.response.let {
            when(it.status.value) {
                BAD_REQUEST_CODE -> NetworkBadRequestException(message = it.readText(Charset.forName("UTF-8")), cause = responseException)
                UNAUTHORIZED_CODE -> NetworkUnauthorizedException(message = it.readText(Charset.forName("UTF-8")), cause = responseException)
                FORBIDDEN_CODE -> NetworkForbiddenException(message = it.readText(Charset.forName("UTF-8")), cause = responseException)
                NOT_FOUND_CODE -> NetworkNoResultException(message = it.readText(Charset.forName("UTF-8")), cause = responseException)
                INTERNAL_SERVER_ERROR_CODE -> NetworkErrorException(message = it.readText(Charset.forName("UTF-8")), cause = responseException)
                CONFLICT_ERROR_CODE -> NetworkUnverifiedAccountException(message = it.readText(
                    Charset.forName("UTF-8")), cause = responseException)
                else -> NetworkErrorException()
            }
        }
}