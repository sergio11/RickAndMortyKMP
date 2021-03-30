package sanchez.sergio.kmp_test.domain.interact

import sanchez.sergio.kmp_test.domain.models.Episode
import sanchez.sergio.kmp_test.domain.models.PageData
import sanchez.sergio.kmp_test.persistence.api.episodes.IEpisodeRepository

/**
 * Get Episodes Interact
 * @param episodeRepository
 */
class GetEpisodesInteract (
    private val episodeRepository: IEpisodeRepository) {

    /**
     * Execute
     * @param onSuccess
     * @param onError
     */
    suspend fun execute(
        params: Params,
        onSuccess: (pageData: PageData<Episode>) -> Unit,
        onError: (ex: Exception) -> Unit) = try {
        onSuccess(episodeRepository.findPaginatedList(params.page))
    } catch (ex: Exception) {
        onError(ex)
    }

    data class Params(
        val page: Long
    )

}