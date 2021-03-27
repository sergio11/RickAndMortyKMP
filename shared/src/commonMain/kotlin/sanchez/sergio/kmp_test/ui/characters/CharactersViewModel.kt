package sanchez.sergio.kmp_test.ui.characters

import dev.icerock.moko.mvvm.livedata.LiveData
import dev.icerock.moko.mvvm.livedata.MutableLiveData
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject
import sanchez.sergio.kmp_test.domain.interact.GetCharactersInteract
import sanchez.sergio.kmp_test.domain.models.Character

/**
 * Characters View Model
 */
class CharactersViewModel: ViewModel(), KoinComponent {

    private val getCharactersInteract: GetCharactersInteract by inject()

    /**
     * Live Data Definitions
     */
    private val _state: MutableLiveData<CharactersState> by lazy {
        MutableLiveData(CharactersState.OnLoading)
    }

    val state: LiveData<CharactersState> = _state
    
    fun load() = viewModelScope.launch {

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