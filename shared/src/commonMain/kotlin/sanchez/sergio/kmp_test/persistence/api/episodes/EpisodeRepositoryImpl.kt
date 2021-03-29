package sanchez.sergio.kmp_test.persistence.api.episodes

import sanchez.sergio.kmp_test.domain.models.Episode
import sanchez.sergio.kmp_test.persistence.api.RepoErrorException
import sanchez.sergio.kmp_test.persistence.api.RepoNoResultException
import sanchez.sergio.kmp_test.persistence.network.exception.NetworkNoResultException
import sanchez.sergio.kmp_test.persistence.network.repository.episodes.IEpisodesNetworkRepository

/**
 * Episode Repository Impl
 * @param episodesNetworkRepository
 */
class EpisodeRepositoryImpl(
    private val episodesNetworkRepository: IEpisodesNetworkRepository
): IEpisodeRepository {

    /**
     * Find Paginated List
     * @param page
     */
    @Throws(RepoErrorException::class, RepoNoResultException::class)
    override suspend fun findPaginatedList(page: Int): List<Episode> = try {
        episodesNetworkRepository.findPaginatedList(page)
    } catch (ex: NetworkNoResultException) {
        throw RepoNoResultException(ex)
    } catch (ex: Exception) {
        throw RepoErrorException(ex)
    }

    /**
     * Find By Id
     * @param id
     */
    @Throws(RepoErrorException::class, RepoNoResultException::class)
    override suspend fun findById(id: Int): Episode = try {
        episodesNetworkRepository.findById(id)
    } catch (ex: NetworkNoResultException) {
        throw RepoNoResultException(ex)
    } catch (ex: Exception) {
        throw RepoErrorException(ex)
    }
}