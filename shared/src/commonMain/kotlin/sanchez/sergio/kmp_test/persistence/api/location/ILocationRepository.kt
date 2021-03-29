package sanchez.sergio.kmp_test.persistence.api.location

import sanchez.sergio.kmp_test.domain.models.Location
import sanchez.sergio.kmp_test.persistence.api.RepoErrorException
import sanchez.sergio.kmp_test.persistence.api.RepoNoResultException

/**
 * Location Repository
 */
interface ILocationRepository {

    /**
     * Find Paginated List
     * @param page
     */
    @Throws(RepoErrorException::class, RepoNoResultException::class)
    suspend fun findPaginatedList(page: Int): List<Location>

    /**
     * Find By Id
     */
    @Throws(RepoErrorException::class, RepoNoResultException::class)
    suspend fun findById(id: Int): Location

}