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
        onSuccess: (charactersList: List<Character>) -> Unit,
        onError: (ex: Exception) -> Unit) = try {
        onSuccess(emptyList())
    } catch (ex: Exception) {
        onError(ex)
    }
}