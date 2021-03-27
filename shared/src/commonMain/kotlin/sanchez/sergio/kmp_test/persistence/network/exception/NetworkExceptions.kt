package sanchez.sergio.kmp_test.persistence.network.exception

/**
 * Network Exceptions
 */

class NetworkBadRequestException(message: String? = null, cause: Throwable? = null):
    NetworkException(message, cause)

class NetworkConnectException(message: String? = null, cause: Throwable? = null):
    NetworkException(message, cause)

class NetworkErrorException(message: String? = null, cause: Throwable? = null):
    NetworkException(message, cause)

class NetworkForbiddenException(message: String? = null, cause: Throwable? = null):
    NetworkException(message, cause)

open class NetworkNoInternetException(message: String? = null, cause: Throwable? = null):
    NetworkException(message, cause)

class NetworkNoResultException(message: String? = null, cause: Throwable? = null):
    NetworkException(message, cause)

class NetworkUnauthorizedException(message: String? = null, cause: Throwable? = null):
    NetworkException(message, cause)

class NetworkUnverifiedAccountException(message: String? = null, cause: Throwable? = null):
    NetworkException(message, cause)

open class NetworkException(message: String? = null, cause: Throwable? = null): Exception(message, cause)