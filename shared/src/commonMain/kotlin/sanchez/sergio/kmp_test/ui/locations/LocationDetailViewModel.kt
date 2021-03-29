package sanchez.sergio.kmp_test.ui.locations

import dev.icerock.moko.mvvm.livedata.LiveData
import dev.icerock.moko.mvvm.livedata.MutableLiveData
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.launch
import sanchez.sergio.kmp_test.domain.interact.GetLocationByIdInteract
import sanchez.sergio.kmp_test.domain.models.Location

/**
 * Location Detail View Model
 * @param getLocationByIdInteract
 */
class LocationDetailViewModel(
    private val getLocationByIdInteract: GetLocationByIdInteract
): ViewModel() {

    /**
     * Live Data Definitions
     */
    private val _state: MutableLiveData<LocationDetailState> by lazy {
        MutableLiveData(LocationDetailState.OnLoading)
    }

    val state: LiveData<LocationDetailState> = _state

    /**
     * Public API
     */
    fun load(id: Int) = viewModelScope.launch {
        _state.postValue(LocationDetailState.OnLoading)
        getLocationByIdInteract.execute(
            params = GetLocationByIdInteract.Params(id),
            onSuccess = fun(episode) {
                _state.postValue(LocationDetailState.OnSuccess(episode))
            },
            onError = fun(ex) {
                _state.postValue(LocationDetailState.OnError(ex))
            }
        )
    }

}

sealed class LocationDetailState {

    /**
     * On Loading
     */
    object OnLoading: LocationDetailState()

    /**
     * On Success
     * @param location
     */
    data class OnSuccess(val location: Location): LocationDetailState()

    /**
     * On Error
     * @param error
     */
    data class OnError(val error: Exception): LocationDetailState()

}