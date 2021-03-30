package sanchez.sergio.kmp_test.domain.interact

import sanchez.sergio.kmp_test.domain.models.Location
import sanchez.sergio.kmp_test.domain.models.PageData
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
        onSuccess: (pageData: PageData<Location>) -> Unit,
        onError: (ex: Exception) -> Unit) = try {
        onSuccess(locationRepository.findPaginatedList(params.page))
    } catch (ex: Exception) {
        onError(ex)
    }

    data class Params(
        val page: Long
    )

}