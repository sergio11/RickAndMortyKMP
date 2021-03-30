package sanchez.sergio.kmp_test.ui.locations

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

/**
 * Locations List Bindings
 */
object LocationsListBindings {

    /**
     * Bind Swipe Refresh State
     * @param view
     * @param state
     */
    @JvmStatic
    @BindingAdapter("bindSwipeRefreshState")
    fun bindSwipeRefreshState(view: SwipeRefreshLayout, state: LocationsState) {
        view.isRefreshing = state is LocationsState.OnLoading
    }

    /**
     * Bind Visibility By State
     * @param view
     * @param state
     */
    @JvmStatic
    @BindingAdapter("bindVisibilityByState")
    fun bindVisibilityByState(view: RecyclerView, state: LocationsState) {
        view.visibility = if (state is LocationsState.OnSuccess)
            View.VISIBLE else View.GONE
    }

    /**
     * Bind Adapter Data
     */
    @JvmStatic
    @BindingAdapter("bindAdapterData")
    fun bindAdapterData(view: RecyclerView, state: LocationsState) {
        val adapter = view.adapter
        if(state is LocationsState.OnSuccess &&  adapter is LocationListAdapter)
            adapter.addData(state.locationList)

    }
}