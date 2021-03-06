package sanchez.sergio.kmp_test.persistence.api.episodes

import sanchez.sergio.kmp_test.domain.models.Episode
import sanchez.sergio.kmp_test.domain.models.PageData
import sanchez.sergio.kmp_test.persistence.api.RepoErrorException
import sanchez.sergio.kmp_test.persistence.api.RepoNoResultException
import sanchez.sergio.kmp_test.persistence.db.repository.core.IDatabaseRepository
import sanchez.sergio.kmp_test.persistence.network.exception.NetworkNoResultException
import sanchez.sergio.kmp_test.persistence.network.repository.episodes.IEpisodesNetworkRepository
import kotlin.coroutines.cancellation.CancellationException

/**
 * Episode Repository Impl
 * @param episodesNetworkRepository
 * @param episodesDatabaseRepository
 */
class EpisodeRepositoryImpl(
    private val episodesNetworkRepository: IEpisodesNetworkRepository,
    private val episodesDatabaseRepository: IDatabaseRepository<Episode>
): IEpisodeRepository {

    /**
     * Find Paginated List
     * @param page
     */
    @Throws(RepoErrorException::class, RepoNoResultException::class, CancellationException::class)
    override suspend fun findPaginatedList(page: Long): PageData<Episode> = try {
        episodesNetworkRepository.findPaginatedList(page).also {
            episodesDatabaseRepository.save(it.data)
        }
    } catch (ex: Exception) {
        try {
            PageData(data = episodesDatabaseRepository.findAll(), isLast = true)
        } catch (ex: Exception) {
            throw RepoErrorException(ex)
        }
    }

    /**
     * Find By Id
     * @param id
     */
    @Throws(RepoErrorException::class, RepoNoResultException::class, CancellationException::class)
    override suspend fun findById(id: Int): Episode = try {
        episodesNetworkRepository.findById(id)
    } catch (ex: NetworkNoResultException) {
        throw RepoNoResultException(ex)
    } catch (ex: Exception) {
        throw RepoErrorException(ex)
    }
}