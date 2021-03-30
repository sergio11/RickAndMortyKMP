package sanchez.sergio.kmp_test.persistence.network.repository.episodes

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import sanchez.sergio.kmp_test.domain.models.Episode
import sanchez.sergio.kmp_test.domain.models.PageData
import sanchez.sergio.kmp_test.persistence.network.exception.NetworkNoResultException
import sanchez.sergio.kmp_test.persistence.network.mapper.episodes.EpisodeNetworkMapper
import sanchez.sergio.kmp_test.persistence.network.models.EpisodeDTO
import sanchez.sergio.kmp_test.persistence.network.models.EpisodesResponseDTO
import sanchez.sergio.kmp_test.persistence.network.repository.core.SupportNetworkRepository

/**
 * Episodes Network Repository Impl
 * @param httpClient
 * @param baseUrl
 * @param episodesNetworkMapper
 */
class EpisodesNetworkRepositoryImpl(
    private val httpClient: HttpClient,
    private val baseUrl: String,
    private val episodesNetworkMapper: EpisodeNetworkMapper
): SupportNetworkRepository(), IEpisodesNetworkRepository {

    /**
     * Find Paginated list
     * @param page
     */
    override suspend fun findPaginatedList(page: Long): PageData<Episode> = safeNetworkCall {
        val pageResults = httpClient.get<EpisodesResponseDTO>("$baseUrl/episode") {
            parameter("page", page)
        }

        if(pageResults.results.isEmpty())
            throw NetworkNoResultException("Not Episodes found")

        val data = episodesNetworkMapper.dtoToModel(pageResults.results)

        PageData(
            page = page,
            data = data,
            isLast = pageResults.info.next.isNullOrBlank()
        )
    }

    /**
     * Find By Id
     * @param id
     */
    override suspend fun findById(id: Int): Episode = safeNetworkCall {
        val episodeDTO = httpClient.get<EpisodeDTO>("$baseUrl/episode/$id")
        episodesNetworkMapper.dtoToModel(episodeDTO)
    }
}