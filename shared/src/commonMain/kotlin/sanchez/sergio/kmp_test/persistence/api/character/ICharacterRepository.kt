package sanchez.sergio.kmp_test.persistence.api.character

import sanchez.sergio.kmp_test.domain.models.Character
import sanchez.sergio.kmp_test.persistence.api.RepoErrorException
import sanchez.sergio.kmp_test.persistence.api.RepoNoResultException

/**
 * Character Repository
 */
interface ICharacterRepository {

    /**
     * Find All Paged
     * @param page
     */
    @Throws(RepoErrorException::class, RepoNoResultException::class)
    suspend fun findAllPaged(page: Int): List<Character>
}