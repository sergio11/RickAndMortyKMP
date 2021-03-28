package sanchez.sergio.kmp_test.domain.interact

import sanchez.sergio.kmp_test.domain.models.Character
import sanchez.sergio.kmp_test.persistence.api.character.ICharacterRepository

/**
 * Get Characters Interact
 * @param characterRepository
 */
class GetCharactersInteract (
    private val characterRepository: ICharacterRepository) {

    /**
     * Execute
     * @param onSuccess
     * @param onError
     */
    suspend fun execute(
        params: Params,
        onSuccess: (charactersList: List<Character>) -> Unit,
        onError: (ex: Exception) -> Unit) = try {
        onSuccess(characterRepository.findAllPaged(params.page))
    } catch (ex: Exception) {
        onError(ex)
    }

    data class Params(
        val page: Int
    )

}