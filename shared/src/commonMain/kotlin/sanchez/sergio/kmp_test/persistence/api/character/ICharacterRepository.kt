package sanchez.sergio.kmp_test.persistence.api.character

import sanchez.sergio.kmp_test.domain.models.Character
import sanchez.sergio.kmp_test.persistence.api.RepoErrorException
import sanchez.sergio.kmp_test.persistence.api.RepoNoResultException

/**
 * Character Repository
 */
interface ICharacterRepository {

    /**
     * Find Paginated List
     * @param page
     */
    @Throws(RepoErrorException::class, RepoNoResultException::class)
    suspend fun findPaginatedList(page: Int): List<Character>

    /**
     * Find By Id
     */
    @Throws(RepoErrorException::class, RepoNoResultException::class)
    suspend fun findById(id: Int): Character
}