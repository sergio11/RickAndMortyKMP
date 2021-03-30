package sanchez.sergio.kmp_test.ui.episodes

import dev.icerock.moko.mvvm.livedata.LiveData
import dev.icerock.moko.mvvm.livedata.MutableLiveData
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.launch
import sanchez.sergio.kmp_test.domain.interact.GetEpisodesInteract
import sanchez.sergio.kmp_test.domain.models.Episode
import sanchez.sergio.kmp_test.domain.models.PageData

/**
 * Episodes View Model
 * @param getEpisodesInteract
 */
class EpisodesViewModel(
    private val getEpisodesInteract: GetEpisodesInteract
): ViewModel() {

    /**
     * Live Data Definitions
     */
    private val _state: MutableLiveData<EpisodesState> by lazy {
        MutableLiveData(EpisodesState.OnIdle)
    }

    val state: LiveData<EpisodesState> = _state

    /**
     * Public API
     */

    fun isLoading() = _state.value is EpisodesState.OnLoading

    /**
     * is last page
     */
    fun isLastPage() = _state.value.let { state ->
        state is EpisodesState.OnSuccess &&
            state.pageData.isLast
    }

    /**
     * Load next page
     */
    fun loadNextPage() = viewModelScope.launch {

        val page = _state.value.let { state ->
            if(state is EpisodesState.OnSuccess)
                state.pageData.page + 1
            else
                DEFAULT_PAGE_NUMBER
        }

        load(page)
        
    }

    /**
     * Load data
     */
    fun load(page: Long = DEFAULT_PAGE_NUMBER) = viewModelScope.launch {
        _state.postValue(EpisodesState.OnLoading)
        getEpisodesInteract.execute(
            params = GetEpisodesInteract.Params(page),
            onSuccess = fun(pageData) {
                _state.postValue(EpisodesState.OnSuccess(pageData))
            },
            onError = fun(ex) {
                _state.postValue(EpisodesState.OnError(ex))
            }
        )

    }

    companion object {
        private const val DEFAULT_PAGE_NUMBER = 1L
    }
    
}


sealed class EpisodesState {

    /**
     * On Idle
     */
    object OnIdle: EpisodesState()

    /**
     * On Loading
     */
    object OnLoading: EpisodesState()

    /**
     * On Success
     * @param pageData
     */
    data class OnSuccess(val pageData: PageData<Episode>): EpisodesState()

    /**
     * On Error
     * @param error
     */
    data class OnError(val error: Exception): EpisodesState()

}