package sanchez.sergio.kmp_test.ui.locations

import dev.icerock.moko.mvvm.livedata.LiveData
import dev.icerock.moko.mvvm.livedata.MutableLiveData
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.launch
import sanchez.sergio.kmp_test.domain.interact.GetLocationsInteract
import sanchez.sergio.kmp_test.domain.models.Location
import sanchez.sergio.kmp_test.ui.episodes.EpisodesState

/**
 * Locations View Model
 * @param getLocationsInteract
 */
class LocationsViewModel(
    private val getLocationsInteract: GetLocationsInteract
): ViewModel() {

    /**
     * Live Data Definitions
     */
    private val _state: MutableLiveData<LocationsState> by lazy {
        MutableLiveData(LocationsState.OnLoading)
    }

    val state: LiveData<LocationsState> = _state

    /**
     * Public API
     */

    fun isLoading() = _state.value is LocationsState.OnLoading

    fun load() = viewModelScope.launch {
        _state.postValue(LocationsState.OnLoading)
        getLocationsInteract.execute(
            params = GetLocationsInteract.Params(page = 1),
            onSuccess = fun(characterList) {
                _state.postValue(LocationsState.OnSuccess(characterList))
            },
            onError = fun(ex) {
                _state.postValue(LocationsState.OnError(ex))
            }
        )
    }

}

sealed class LocationsState {

    /**
     * On Loading
     */
    object OnLoading: LocationsState()

    /**
     * On Success
     * @param locationList
     */
    data class OnSuccess(val locationList: List<Location>): LocationsState()

    /**
     * On Error
     * @param error
     */
    data class OnError(val error: Exception): LocationsState()

}