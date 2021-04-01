package sanchez.sergio.kmp_test.ui.locations

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import sanchez.sergio.kmp_test.R
import sanchez.sergio.kmp_test.domain.models.Location

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
            adapter.addData(state.pageData.data)
    }

    /**
     * Bind Location Created at
     * @param view
     * @param location
     */
    @SuppressLint("SetTextI18n")
    @JvmStatic
    @BindingAdapter("bindLocationCreatedAt")
    fun bindLocationCreatedAt(view: TextView, location: Location) {
        view.text = String.format(view.context.getString(R.string.location_created_at),
            location.created)
    }

    /**
     * Bind Location Type
     * @param view
     * @param location
     */
    @SuppressLint("SetTextI18n")
    @JvmStatic
    @BindingAdapter("bindLocationType")
    fun bindLocationType(view: TextView, location: Location) {
        view.text = String.format(view.context.getString(R.string.location_type),
            location.type)
    }


    /**
     * Bind Location Residents
     * @param view
     * @param location
     */
    @SuppressLint("SetTextI18n")
    @JvmStatic
    @BindingAdapter("bindLocationResidents")
    fun bindLocationResidents(view: TextView, location: Location) {
        view.text = String.format(view.context.getString(R.string.location_residents),
            location.residents)
    }

    /**
     * Bind Location Dimension
     * @param view
     * @param location
     */
    @SuppressLint("SetTextI18n")
    @JvmStatic
    @BindingAdapter("bindLocationDimension")
    fun bindLocationDimension(view: TextView, location: Location) {
        view.text = String.format(view.context.getString(R.string.location_dimension),
            location.dimension)
    }


}