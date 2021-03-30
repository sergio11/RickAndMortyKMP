package sanchez.sergio.kmp_test.persistence.network.repository.character

import sanchez.sergio.kmp_test.domain.models.Character
import sanchez.sergio.kmp_test.domain.models.PageData
import sanchez.sergio.kmp_test.persistence.network.exception.NetworkException
import kotlin.coroutines.cancellation.CancellationException

/**
 * Character Network Repository
 */
interface ICharacterNetworkRepository {

    /**
     * Find Paginated List
     * @param page
     */
    @Throws(NetworkException::class, CancellationException::class)
    suspend fun findPaginatedList(page: Long): PageData<Character>

    /**
     * Find By Id
     * @param id
     */
    @Throws(NetworkException::class, CancellationException::class)
    suspend fun findById(id: Int): Character

}