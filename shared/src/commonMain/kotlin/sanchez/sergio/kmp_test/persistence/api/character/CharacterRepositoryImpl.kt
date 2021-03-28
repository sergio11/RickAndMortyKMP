package sanchez.sergio.kmp_test.persistence.api.character

import sanchez.sergio.kmp_test.domain.models.Character
import sanchez.sergio.kmp_test.persistence.api.RepoErrorException
import sanchez.sergio.kmp_test.persistence.api.RepoNoResultException
import sanchez.sergio.kmp_test.persistence.network.exception.NetworkNoResultException

/**
 * Character Repository Impl
 * @param characterNetworkRepository
 */
class CharacterRepositoryImpl(
    private val characterNetworkRepository: ICharacterRepository): ICharacterRepository {

    /**
     * Find All Paged
     * @param page
     */
    @Throws(RepoErrorException::class, RepoNoResultException::class)
    override suspend fun findAllPaged(page: Int): List<Character> = try {
        characterNetworkRepository.findAllPaged(page)
    } catch (ex: NetworkNoResultException) {
        throw RepoNoResultException(ex)
    } catch (ex: Exception) {
        throw RepoErrorException(ex)
    }
}