package sanchez.sergio.kmp_test.ui.characters

import dev.icerock.moko.mvvm.livedata.LiveData
import dev.icerock.moko.mvvm.livedata.MutableLiveData
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.launch
import sanchez.sergio.kmp_test.domain.interact.GetCharactersInteract
import sanchez.sergio.kmp_test.domain.models.Character


/**
 * Characters View Model
 */
class CharactersViewModel(
    private val getCharactersInteract: GetCharactersInteract
): ViewModel() {

    /**
     * Live Data Definitions
     */
    private val _state: MutableLiveData<CharactersState> by lazy {
        MutableLiveData(CharactersState.OnLoading)
    }

    val state: LiveData<CharactersState> = _state

    /**
     * Public API
     */

    fun load() = viewModelScope.launch {
        _state.postValue(CharactersState.OnLoading)
        getCharactersInteract.execute(
            params = GetCharactersInteract.Params(page = 1),
            onSuccess = fun(characterList) {
                _state.postValue(CharactersState.OnSuccess(characterList))
            },
            onError = fun(ex) {
                _state.postValue(CharactersState.OnError(ex))
            }
        )
    }
}

sealed class CharactersState {

    /**
     * On Loading
     */
    object OnLoading: CharactersState()

    /**
     * On Success
     * @param characterList
     */
    data class OnSuccess(val characterList: List<Character>): CharactersState()

    /**
     * On Error
     * @param error
     */
    data class OnError(val error: Exception): CharactersState()

}