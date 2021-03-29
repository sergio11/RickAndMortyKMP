package sanchez.sergio.kmp_test.domain.interact

import sanchez.sergio.kmp_test.domain.models.Location
import sanchez.sergio.kmp_test.persistence.api.location.ILocationRepository

/**
 * Get Location By Id Interact
 * @param locationRepository
 */
class GetLocationByIdInteract (
    private val locationRepository: ILocationRepository) {

    /**
     * Execute
     * @param onSuccess
     * @param onError
     */
    suspend fun execute(
        params: Params,
        onSuccess: (location: Location) -> Unit,
        onError: (ex: Exception) -> Unit) = try {
        onSuccess(locationRepository.findById(params.id))
    } catch (ex: Exception) {
        onError(ex)
    }

    data class Params(
        val id: Int
    )

}