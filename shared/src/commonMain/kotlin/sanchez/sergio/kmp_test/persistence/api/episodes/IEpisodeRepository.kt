package sanchez.sergio.kmp_test.persistence.api.episodes

import sanchez.sergio.kmp_test.domain.models.Episode
import sanchez.sergio.kmp_test.persistence.api.RepoErrorException
import sanchez.sergio.kmp_test.persistence.api.RepoNoResultException
import kotlin.coroutines.cancellation.CancellationException

/**
 * Episode Repository
 */
interface IEpisodeRepository {

    /**
     * Find Paginated List
     * @param page
     */
    @Throws(RepoErrorException::class, RepoNoResultException::class, CancellationException::class)
    suspend fun findPaginatedList(page: Int): List<Episode>

    /**
     * Find By Id
     */
    @Throws(RepoErrorException::class, RepoNoResultException::class, CancellationException::class)
    suspend fun findById(id: Int): Episode
}