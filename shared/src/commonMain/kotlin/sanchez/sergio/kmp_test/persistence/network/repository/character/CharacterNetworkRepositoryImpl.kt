package sanchez.sergio.kmp_test.persistence.network.repository.character

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import sanchez.sergio.kmp_test.domain.models.Character
import sanchez.sergio.kmp_test.domain.models.PageData
import sanchez.sergio.kmp_test.persistence.network.exception.NetworkException
import sanchez.sergio.kmp_test.persistence.network.exception.NetworkNoResultException
import sanchez.sergio.kmp_test.persistence.network.mapper.character.CharacterNetworkMapper
import sanchez.sergio.kmp_test.persistence.network.models.CharacterDTO
import sanchez.sergio.kmp_test.persistence.network.models.CharactersResponseDTO
import sanchez.sergio.kmp_test.persistence.network.repository.core.SupportNetworkRepository
import kotlin.coroutines.cancellation.CancellationException

/**
 * Character Repository Impl
 * @param httpClient
 * @param baseUrl
 * @param characterNetworkMapper
 */
open class CharacterNetworkRepositoryImpl(
    private val httpClient: HttpClient,
    private val baseUrl: String,
    private val characterNetworkMapper: CharacterNetworkMapper
): SupportNetworkRepository(), ICharacterNetworkRepository {

    /**
     * Find all paged
     * @param page
     */
    @Throws(NetworkException::class, CancellationException::class)
    override suspend fun findPaginatedList(page: Long): PageData<Character> = safeNetworkCall {

        val pageResults = httpClient.get<CharactersResponseDTO>("$baseUrl/character") {
            parameter("page", page)
        }

        if(pageResults.results.isEmpty())
            throw NetworkNoResultException("Not Characters found")

        val data = characterNetworkMapper.dtoToModel(pageResults.results)

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
    @Throws(NetworkException::class, CancellationException::class)
    override suspend fun findById(id: Int): Character = safeNetworkCall {
        val characterDTO = httpClient.get<CharacterDTO>("$baseUrl/character/$id")
        characterNetworkMapper.dtoToModel(characterDTO)
    }
}