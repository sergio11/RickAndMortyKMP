package sanchez.sergio.kmp_test.ui.characters

import co.touchlab.kermit.Kermit
import dev.icerock.moko.mvvm.livedata.LiveData
import dev.icerock.moko.mvvm.livedata.MutableLiveData
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.launch
import sanchez.sergio.kmp_test.domain.interact.GetCharactersInteract
import sanchez.sergio.kmp_test.domain.models.Character
import sanchez.sergio.kmp_test.domain.models.PageData

/**
 * Characters View Model
 */
class CharactersViewModel(
    private val getCharactersInteract: GetCharactersInteract,
    private val kermit: Kermit
): ViewModel() {

    /**
     * Live Data Definitions
     */
    private val _state: MutableLiveData<CharactersState> by lazy {
        MutableLiveData(CharactersState.OnIdle)
    }

    val state: LiveData<CharactersState> = _state

    /**
     * Public API
     */

    fun isLoading() = _state.value is CharactersState.OnLoading

    /**
     * is last page
     */
    fun isLastPage() = _state.value.let { state ->
        state is CharactersState.OnSuccess &&
            state.pageData.isLast
    }

    /**
     * Load next page
     */
    fun loadNextPage() = viewModelScope.launch {

        val page = _state.value.let { state ->
            if(state is CharactersState.OnSuccess)
                state.pageData.page + 1
            else
                DEFAULT_PAGE_NUMBER
        }

        load(page)

    }

    /**
     * Load
     * @param page
     */
    fun load(page: Long = DEFAULT_PAGE_NUMBER) = viewModelScope.launch {
        kermit.d { "load CALLED" }
        _state.postValue(CharactersState.OnLoading)
        getCharactersInteract.execute(
            params = GetCharactersInteract.Params(page),
            onSuccess = fun(pageData) {
                kermit.d { "onSuccess SIZE ${pageData.data.size} CALLED" }
                _state.postValue(CharactersState.OnSuccess(pageData))
            },
            onError = fun(ex) {
                kermit.d { "onError CALLED" }
                _state.postValue(CharactersState.OnError(ex))
            }
        )
    }

    companion object {
        private const val DEFAULT_PAGE_NUMBER = 1L
    }
}

sealed class CharactersState {

    /**
     * On Idle
     */
    object OnIdle: CharactersState()

    /**
     * On Loading
     */
    object OnLoading: CharactersState()

    /**
     * On Success
     * @param pageData
     */
    data class OnSuccess(val pageData: PageData<Character>): CharactersState()

    /**
     * On Error
     * @param error
     */
    data class OnError(val error: Exception): CharactersState()

}