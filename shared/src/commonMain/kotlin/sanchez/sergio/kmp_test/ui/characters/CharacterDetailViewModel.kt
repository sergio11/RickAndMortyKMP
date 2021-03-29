package sanchez.sergio.kmp_test.ui.characters

import dev.icerock.moko.mvvm.livedata.LiveData
import dev.icerock.moko.mvvm.livedata.MutableLiveData
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.launch
import sanchez.sergio.kmp_test.domain.interact.GetCharacterByIdInteract
import sanchez.sergio.kmp_test.domain.models.Character

/**
 * Character Detail View Model
 * @param getCharacterByIdInteract
 */
class CharacterDetailViewModel(
    private val getCharacterByIdInteract: GetCharacterByIdInteract
): ViewModel() {

    /**
     * Live Data Definitions
     */
    private val _state: MutableLiveData<CharacterDetailState> by lazy {
        MutableLiveData(CharacterDetailState.OnLoading)
    }

    val state: LiveData<CharacterDetailState> = _state

    /**
     * Public API
     */

    fun load(id: Int) = viewModelScope.launch {
        _state.postValue(CharacterDetailState.OnLoading)
        getCharacterByIdInteract.execute(
            params = GetCharacterByIdInteract.Params(id),
            onSuccess = fun(character) {
                _state.postValue(CharacterDetailState.OnSuccess(character))
            },
            onError = fun(ex) {
                _state.postValue(CharacterDetailState.OnError(ex))
            }
        )

    }

}

sealed class CharacterDetailState {

    /**
     * On Loading
     */
    object OnLoading: CharacterDetailState()

    /**
     * On Success
     * @param character
     */
    data class OnSuccess(val character: Character): CharacterDetailState()

    /**
     * On Error
     * @param error
     */
    data class OnError(val error: Exception): CharacterDetailState()

}