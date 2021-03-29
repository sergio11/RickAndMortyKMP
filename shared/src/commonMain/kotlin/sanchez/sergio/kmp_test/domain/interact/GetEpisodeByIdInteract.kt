package sanchez.sergio.kmp_test.domain.interact

import sanchez.sergio.kmp_test.domain.models.Episode
import sanchez.sergio.kmp_test.persistence.api.episodes.IEpisodeRepository

/**
 * Get Episode By Id Interact
 * @param episodeRepository
 */
class GetEpisodeByIdInteract (
    private val episodeRepository: IEpisodeRepository) {

    /**
     * Execute
     * @param onSuccess
     * @param onError
     */
    suspend fun execute(
        params: Params,
        onSuccess: (episode: Episode) -> Unit,
        onError: (ex: Exception) -> Unit) = try {
        onSuccess(episodeRepository.findById(params.id))
    } catch (ex: Exception) {
        onError(ex)
    }

    data class Params(
        val id: Int
    )

}