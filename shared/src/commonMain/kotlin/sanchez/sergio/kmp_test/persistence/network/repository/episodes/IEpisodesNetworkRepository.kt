package sanchez.sergio.kmp_test.persistence.network.repository.episodes

import sanchez.sergio.kmp_test.domain.models.Episode
import sanchez.sergio.kmp_test.persistence.network.exception.NetworkException

/**
 * Episodes Network Repository
 */
interface IEpisodesNetworkRepository {

    /**
     * Find Paginated List
     * @param page
     */
    @Throws(NetworkException::class)
    suspend fun findPaginatedList(page: Int): List<Episode>

    /**
     * Find by id
     * @param id
     */
    @Throws(NetworkException::class)
    suspend fun findById(id: Int): Episode

}