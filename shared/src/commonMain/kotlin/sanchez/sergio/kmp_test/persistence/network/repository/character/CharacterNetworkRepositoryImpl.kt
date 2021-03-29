package sanchez.sergio.kmp_test.persistence.network.repository.character

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import sanchez.sergio.kmp_test.domain.models.Character
import sanchez.sergio.kmp_test.persistence.network.exception.NetworkException
import sanchez.sergio.kmp_test.persistence.network.exception.NetworkNoResultException
import sanchez.sergio.kmp_test.persistence.network.mapper.CharacterNetworkMapper
import sanchez.sergio.kmp_test.persistence.network.models.CharacterDTO
import sanchez.sergio.kmp_test.persistence.network.models.CharactersResponseDTO
import sanchez.sergio.kmp_test.persistence.network.repository.core.SupportNetworkRepository

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
    @Throws(NetworkException::class)
    override suspend fun findPaginatedList(page: Int): List<Character> = safeNetworkCall {

        val dataResult = httpClient.get<CharactersResponseDTO>("$baseUrl/character") {
            parameter("page", page)
        }.results

        if(dataResult.isEmpty())
            throw NetworkNoResultException("Not Characters found")

        characterNetworkMapper.dtoToModel(dataResult)

    }

    /**
     * Find By Id
     * @param id
     */
    @Throws(NetworkException::class)
    override suspend fun findById(id: Int): Character = safeNetworkCall {
        val characterDTO = httpClient.get<CharacterDTO>("$baseUrl/character/$id")
        characterNetworkMapper.dtoToModel(characterDTO)
    }
}