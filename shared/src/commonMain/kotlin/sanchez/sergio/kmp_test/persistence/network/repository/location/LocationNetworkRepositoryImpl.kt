package sanchez.sergio.kmp_test.persistence.network.repository.location

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import sanchez.sergio.kmp_test.domain.models.Location
import sanchez.sergio.kmp_test.domain.models.PageData
import sanchez.sergio.kmp_test.persistence.network.exception.NetworkNoResultException
import sanchez.sergio.kmp_test.persistence.network.mapper.character.LocationNetworkMapper
import sanchez.sergio.kmp_test.persistence.network.models.LocationDTO
import sanchez.sergio.kmp_test.persistence.network.models.LocationsResponseDTO
import sanchez.sergio.kmp_test.persistence.network.repository.core.SupportNetworkRepository

/**
 * Location Network Repository Impl
 * @param httpClient
 * @param baseUrl
 * @param locationNetworkMapper
 */
class LocationNetworkRepositoryImpl(
    private val httpClient: HttpClient,
    private val baseUrl: String,
    private val locationNetworkMapper: LocationNetworkMapper
): SupportNetworkRepository(), ILocationNetworkRepository {

    /**
     * Find paginated list
     * @param page
     */
    override suspend fun findPaginatedList(page: Long): PageData<Location> = safeNetworkCall {

        val pageResults = httpClient.get<LocationsResponseDTO>("$baseUrl/location") {
            parameter("page", page)
        }

        if(pageResults.results.isEmpty())
            throw NetworkNoResultException("Not Locations found")

        val data = locationNetworkMapper.dtoToModel(pageResults.results)

        PageData(
            page = page,
            data = data,
            isLast = pageResults.info.next.isNullOrBlank()
        )
    }

    /**
     * Find by id
     * @param id
     */
    override suspend fun findById(id: Int): Location = safeNetworkCall {
        val episodeDTO = httpClient.get<LocationDTO>("$baseUrl/location/$id")
        locationNetworkMapper.dtoToModel(episodeDTO)
    }
}