package sanchez.sergio.kmp_test.persistence.api.character

import sanchez.sergio.kmp_test.domain.models.Character
import sanchez.sergio.kmp_test.domain.models.PageData
import sanchez.sergio.kmp_test.persistence.api.RepoErrorException
import sanchez.sergio.kmp_test.persistence.api.RepoNoResultException
import kotlin.coroutines.cancellation.CancellationException

/**
 * Character Repository
 */
interface ICharacterRepository {

    /**
     * Find Paginated List
     * @param page
     */
    @Throws(RepoErrorException::class, RepoNoResultException::class, CancellationException::class)
    suspend fun findPaginatedList(page: Long): PageData<Character>

    /**
     * Find By Id
     */
    @Throws(RepoErrorException::class, RepoNoResultException::class, CancellationException::class)
    suspend fun findById(id: Int): Character
}