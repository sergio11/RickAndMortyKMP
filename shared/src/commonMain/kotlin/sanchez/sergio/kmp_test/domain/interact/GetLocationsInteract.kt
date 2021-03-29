package sanchez.sergio.kmp_test.domain.interact

import sanchez.sergio.kmp_test.domain.models.Location
import sanchez.sergio.kmp_test.persistence.api.location.ILocationRepository

/**
 * Get Locations Interact
 * @param locationRepository
 */
class GetLocationsInteract (
    private val locationRepository: ILocationRepository) {

    /**
     * Execute
     * @param onSuccess
     * @param onError
     */
    suspend fun execute(
        params: Params,
        onSuccess: (locationsList: List<Location>) -> Unit,
        onError: (ex: Exception) -> Unit) = try {
        onSuccess(locationRepository.findPaginatedList(params.page))
    } catch (ex: Exception) {
        onError(ex)
    }

    data class Params(
        val page: Int
    )

}