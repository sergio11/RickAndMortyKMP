package sanchez.sergio.kmp_test.ui.locations

import dev.icerock.moko.mvvm.livedata.LiveData
import dev.icerock.moko.mvvm.livedata.MutableLiveData
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.launch
import sanchez.sergio.kmp_test.domain.interact.GetLocationsInteract
import sanchez.sergio.kmp_test.domain.models.Location
import sanchez.sergio.kmp_test.domain.models.PageData
import sanchez.sergio.kmp_test.ui.episodes.EpisodesState
import sanchez.sergio.kmp_test.ui.episodes.EpisodesViewModel

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

    /**
     * is last page
     */
    fun isLastPage() = _state.value.let { state ->
        state is LocationsState.OnSuccess &&
            state.pageData.isLast
    }

    /**
     * Load next page
     */
    fun loadNextPage() = viewModelScope.launch {

        val page = _state.value.let { state ->
            if(state is LocationsState.OnSuccess)
                state.pageData.page + 1
            else
                DEFAULT_PAGE_NUMBER
        }

        load(page)

    }

    fun load(page: Long = DEFAULT_PAGE_NUMBER) = viewModelScope.launch {
        _state.postValue(LocationsState.OnLoading)
        getLocationsInteract.execute(
            params = GetLocationsInteract.Params(page),
            onSuccess = fun(characterList) {
                _state.postValue(LocationsState.OnSuccess(characterList))
            },
            onError = fun(ex) {
                _state.postValue(LocationsState.OnError(ex))
            }
        )
    }

    companion object {
        private const val DEFAULT_PAGE_NUMBER = 1L
    }

}

sealed class LocationsState {

    /**
     * On Loading
     */
    object OnLoading: LocationsState()

    /**
     * On Success
     * @param pageData
     */
    data class OnSuccess(val pageData: PageData<Location>): LocationsState()

    /**
     * On Error
     * @param error
     */
    data class OnError(val error: Exception): LocationsState()

}