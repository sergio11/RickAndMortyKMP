package sanchez.sergio.kmp_test.ui.episodes

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import sanchez.sergio.kmp_test.R
import sanchez.sergio.kmp_test.domain.models.Episode
import sanchez.sergio.kmp_test.domain.models.Location

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
            adapter.addData(state.pageData.data)
    }

    /**
     * Bind Episode Created at
     * @param view
     * @param episode
     */
    @SuppressLint("SetTextI18n")
    @JvmStatic
    @BindingAdapter("bindEpisodeCreatedAt")
    fun bindEpisodeCreatedAt(view: TextView, episode: Episode) {
        view.text = String.format(view.context.getString(R.string.episode_created_at),
            episode.created)
    }

    /**
     * Bind Episode Air Date
     * @param view
     * @param episode
     */
    @SuppressLint("SetTextI18n")
    @JvmStatic
    @BindingAdapter("bindEpisodeAirDate")
    fun bindEpisodeAirDate(view: TextView, episode: Episode) {
        view.text = String.format(view.context.getString(R.string.episode_air_date),
            episode.airDate)
    }

    /**
     * Bind Episode Characters
     * @param view
     * @param episode
     */
    @SuppressLint("SetTextI18n")
    @JvmStatic
    @BindingAdapter("bindEpisodeCharacters")
    fun bindEpisodeCharacters(view: TextView, episode: Episode) {
        view.text = String.format(view.context.getString(R.string.episode_characters),
            episode.characters)
    }
}