package sanchez.sergio.kmp_test.persistence.network.repository.location

import sanchez.sergio.kmp_test.domain.models.Location
import sanchez.sergio.kmp_test.domain.models.PageData
import sanchez.sergio.kmp_test.persistence.network.exception.NetworkException
import kotlin.coroutines.cancellation.CancellationException

/**
 * Location Network Repository
 */
interface ILocationNetworkRepository {

    /**
     * Find Paginated List
     * @param page
     */
    @Throws(NetworkException::class, CancellationException::class)
    suspend fun findPaginatedList(page: Long): PageData<Location>

    /**
     * Find By Id
     * @param id
     */
    @Throws(NetworkException::class, CancellationException::class)
    suspend fun findById(id: Int): Location
}