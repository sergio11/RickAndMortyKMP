package sanchez.sergio.kmp_test.persistence.api.character

import sanchez.sergio.kmp_test.domain.models.Character
import sanchez.sergio.kmp_test.domain.models.PageData
import sanchez.sergio.kmp_test.persistence.api.RepoErrorException
import sanchez.sergio.kmp_test.persistence.api.RepoNoResultException
import sanchez.sergio.kmp_test.persistence.db.repository.core.IDatabaseRepository
import sanchez.sergio.kmp_test.persistence.network.exception.NetworkNoResultException
import sanchez.sergio.kmp_test.persistence.network.repository.character.ICharacterNetworkRepository
import kotlin.coroutines.cancellation.CancellationException

/**
 * Character Repository Impl
 * @param characterNetworkRepository
 * @param characterDatabaseRepository
 */
class CharacterRepositoryImpl(
    private val characterNetworkRepository: ICharacterNetworkRepository,
    private val characterDatabaseRepository: IDatabaseRepository<Character>): ICharacterRepository {

    /**
     * Find All Paged
     * @param page
     */
    @Throws(RepoErrorException::class, RepoNoResultException::class, CancellationException::class)
    override suspend fun findPaginatedList(page: Long): PageData<Character> = try {
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
    @Throws(RepoErrorException::class, RepoNoResultException::class, CancellationException::class)
    override suspend fun findById(id: Int): Character = try {
        characterNetworkRepository.findById(id)
    } catch (ex: NetworkNoResultException) {
        throw RepoNoResultException(ex)
    } catch (ex: Exception) {
        throw RepoErrorException(ex)
    }
}