package sanchez.sergio.kmp_test.persistence.api.character

import sanchez.sergio.kmp_test.domain.models.Character
import sanchez.sergio.kmp_test.persistence.api.RepoErrorException
import sanchez.sergio.kmp_test.persistence.api.RepoNoResultException
import sanchez.sergio.kmp_test.persistence.network.exception.NetworkNoResultException
import sanchez.sergio.kmp_test.persistence.network.repository.character.ICharacterNetworkRepository

/**
 * Character Repository Impl
 * @param characterNetworkRepository
 */
class CharacterRepositoryImpl(
    private val characterNetworkRepository: ICharacterNetworkRepository): ICharacterRepository {

    /**
     * Find All Paged
     * @param page
     */
    @Throws(RepoErrorException::class, RepoNoResultException::class)
    override suspend fun findPaginatedList(page: Int): List<Character> = try {
        characterNetworkRepository.findPaginatedList(page)
    } catch (ex: NetworkNoResultException) {
        throw RepoNoResultException(ex)
    } catch (ex: Exception) {
        throw RepoErrorException(ex)
    }

    /**
     * Find By Id
     * @param id
     */
    @Throws(RepoErrorException::class, RepoNoResultException::class)
    override suspend fun findById(id: Int): Character = try {
        characterNetworkRepository.findById(id)
    } catch (ex: NetworkNoResultException) {
        throw RepoNoResultException(ex)
    } catch (ex: Exception) {
        throw RepoErrorException(ex)
    }
}