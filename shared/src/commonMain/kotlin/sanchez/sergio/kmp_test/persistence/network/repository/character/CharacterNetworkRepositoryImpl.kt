package sanchez.sergio.kmp_test.persistence.network.repository.character

import io.ktor.client.HttpClient
import sanchez.sergio.kmp_test.persistence.network.repository.core.SupportNetworkRepository

/**
 * Character Repository Impl
 * @param httpClient
 */
open class CharacterNetworkRepositoryImpl(
    private val httpClient: HttpClient
): SupportNetworkRepository(), ICharacterNetworkRepository {



}