package sanchez.sergio.kmp_test.ui.episodes

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

/**
 * Episodes List Bindings
 */
object EpisodesListBindings {

    /**
     * Bind Swipe Refresh State
     * @param view
     * @param state
     */
    @JvmStatic
    @BindingAdapter("bindSwipeRefreshState")
    fun bindSwipeRefreshState(view: SwipeRefreshLayout, state: EpisodesState) {
        view.isRefreshing = state is EpisodesState.OnLoading
    }

    /**
     * Bind Visibility By State
     * @param view
     * @param state
     */
    @JvmStatic
    @BindingAdapter("bindVisibilityByState")
    fun bindVisibilityByState(view: RecyclerView, state: EpisodesState) {
        view.visibility = if (state is EpisodesState.OnSuccess)
            View.VISIBLE else View.GONE
    }

    /**
     * Bind Adapter Data
     */
    @JvmStatic
    @BindingAdapter("bindAdapterData")
    fun bindAdapterData(view: RecyclerView, state: EpisodesState) {
        val adapter = view.adapter
        if(state is EpisodesState.OnSuccess &&  adapter is EpisodeListAdapter)
            adapter.addData(state.episodeList)

    }
}