package sanchez.sergio.kmp_test.ui.episodes

import dev.icerock.moko.mvvm.livedata.LiveData
import dev.icerock.moko.mvvm.livedata.MutableLiveData
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.launch
import sanchez.sergio.kmp_test.domain.interact.GetEpisodesInteract
import sanchez.sergio.kmp_test.domain.models.Episode

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

    fun load() = viewModelScope.launch {
        _state.postValue(EpisodesState.OnLoading)
        getEpisodesInteract.execute(
            params = GetEpisodesInteract.Params(page = 1),
            onSuccess = fun(episodes) {
                _state.postValue(EpisodesState.OnSuccess(episodes))
            },
            onError = fun(ex) {
                _state.postValue(EpisodesState.OnError(ex))
            }
        )

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
     * @param episodeList
     */
    data class OnSuccess(val episodeList: List<Episode>): EpisodesState()

    /**
     * On Error
     * @param error
     */
    data class OnError(val error: Exception): EpisodesState()

}