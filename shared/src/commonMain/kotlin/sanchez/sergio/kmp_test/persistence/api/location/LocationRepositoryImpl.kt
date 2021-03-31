package sanchez.sergio.kmp_test.persistence.api.location

import sanchez.sergio.kmp_test.domain.models.Location
import sanchez.sergio.kmp_test.domain.models.PageData
import sanchez.sergio.kmp_test.persistence.api.RepoErrorException
import sanchez.sergio.kmp_test.persistence.api.RepoNoResultException
import sanchez.sergio.kmp_test.persistence.db.repository.core.IDatabaseRepository
import sanchez.sergio.kmp_test.persistence.network.exception.NetworkNoResultException
import sanchez.sergio.kmp_test.persistence.network.repository.location.ILocationNetworkRepository
import kotlin.coroutines.cancellation.CancellationException

/**
 * Location Repository
 * @param locationNetworkRepository
 * @param locationDatabaseRepository
 */
class LocationRepositoryImpl(
    private val locationNetworkRepository: ILocationNetworkRepository,
    private val locationDatabaseRepository: IDatabaseRepository<Location>
): ILocationRepository {

    /**
     * Find Paginated List
     * @param page
     */
    @Throws(RepoErrorException::class, RepoNoResultException::class, CancellationException::class)
    override suspend fun findPaginatedList(page: Long): PageData<Location> = try {
        locationNetworkRepository.findPaginatedList(page)
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
    override suspend fun findById(id: Int): Location  = try {
        locationNetworkRepository.findById(id)
    } catch (ex: NetworkNoResultException) {
        throw RepoNoResultException(ex)
    } catch (ex: Exception) {
        throw RepoErrorException(ex)
    }
}