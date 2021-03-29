package sanchez.sergio.kmp_test.domain.interact

import sanchez.sergio.kmp_test.domain.models.Character
import sanchez.sergio.kmp_test.persistence.api.character.ICharacterRepository

/**
 * Get Character By Id Interact
 * @param characterRepository
 */
class GetCharacterByIdInteract (
    private val characterRepository: ICharacterRepository) {

    /**
     * Execute
     * @param onSuccess
     * @param onError
     */
    suspend fun execute(
        params: Params,
        onSuccess: (character: Character) -> Unit,
        onError: (ex: Exception) -> Unit) = try {
        onSuccess(characterRepository.findById(params.id))
    } catch (ex: Exception) {
        onError(ex)
    }

    data class Params(
        val id: Int
    )

}