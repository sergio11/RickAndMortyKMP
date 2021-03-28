package sanchez.sergio.kmp_test.persistence.network.repository.character

import sanchez.sergio.kmp_test.domain.models.Character
import sanchez.sergio.kmp_test.persistence.network.exception.NetworkException

/**
 * Character Network Repository
 */
interface ICharacterNetworkRepository {

    /**
     * Find Paginated List
     * @param page
     */
    @Throws(NetworkException::class)
    suspend fun findPaginatedList(page: Int): List<Character>

}