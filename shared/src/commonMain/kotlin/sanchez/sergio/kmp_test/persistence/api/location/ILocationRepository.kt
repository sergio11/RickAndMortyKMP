package sanchez.sergio.kmp_test.persistence.api.location

import sanchez.sergio.kmp_test.domain.models.Location
import sanchez.sergio.kmp_test.domain.models.PageData
import sanchez.sergio.kmp_test.persistence.api.RepoErrorException
import sanchez.sergio.kmp_test.persistence.api.RepoNoResultException
import kotlin.coroutines.cancellation.CancellationException

/**
 * Location Repository
 */
interface ILocationRepository {

    /**
     * Find Paginated List
     * @param page
     */
    @Throws(RepoErrorException::class, RepoNoResultException::class, CancellationException::class)
    suspend fun findPaginatedList(page: Long): PageData<Location>

    /**
     * Find By Id
     */
    @Throws(RepoErrorException::class, RepoNoResultException::class, CancellationException::class)
    suspend fun findById(id: Int): Location

}