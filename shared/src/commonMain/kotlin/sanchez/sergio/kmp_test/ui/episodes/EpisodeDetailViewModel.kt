package sanchez.sergio.kmp_test.ui.episodes

import dev.icerock.moko.mvvm.livedata.LiveData
import dev.icerock.moko.mvvm.livedata.MutableLiveData
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.launch
import sanchez.sergio.kmp_test.domain.interact.GetEpisodeByIdInteract
import sanchez.sergio.kmp_test.domain.models.Episode

/**
 * Episode Detail View Model
 * @param getEpisodeByIdInteract
 */
class EpisodeDetailViewModel(
    private val getEpisodeByIdInteract: GetEpisodeByIdInteract
): ViewModel() {

    /**
     * Live Data Definitions
     */
    private val _state: MutableLiveData<EpisodeDetailState> by lazy {
        MutableLiveData(EpisodeDetailState.OnLoading)
    }

    val state: LiveData<EpisodeDetailState> = _state

    /**
     * Public API
     */

    fun load(id: Int) = viewModelScope.launch {
        _state.postValue(EpisodeDetailState.OnLoading)
        getEpisodeByIdInteract.execute(
            params = GetEpisodeByIdInteract.Params(id),
            onSuccess = fun(episode) {
                _state.postValue(EpisodeDetailState.OnSuccess(episode))
            },
            onError = fun(ex) {
                _state.postValue(EpisodeDetailState.OnError(ex))
            }
        )

    }

}

sealed class EpisodeDetailState {

    /**
     * On Loading
     */
    object OnLoading: EpisodeDetailState()

    /**
     * On Success
     * @param episode
     */
    data class OnSuccess(val episode: Episode): EpisodeDetailState()

    /**
     * On Error
     * @param error
     */
    data class OnError(val error: Exception): EpisodeDetailState()

}