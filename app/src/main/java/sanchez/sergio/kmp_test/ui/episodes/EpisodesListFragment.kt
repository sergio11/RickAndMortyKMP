package sanchez.sergio.kmp_test.ui.episodes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.KoinComponent
import sanchez.sergio.kmp_test.core.extension.configure
import sanchez.sergio.kmp_test.databinding.EpisodeListFragmentBinding
import sanchez.sergio.kmp_test.domain.models.Episode

/**
 * Episodes List Fragment
 */
class EpisodesListFragment: Fragment(), KoinComponent, EpisodeListAdapter.OnEpisodeClickListener {

    private val episodesViewModel: EpisodesViewModel by viewModel()
    private val binding by lazy { EpisodeListFragmentBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launchWhenStarted {
            episodesViewModel.state.addObserver { state ->
                with(binding) {
                    uiState = state
                }
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            recyclerView.apply {
                configure(
                    isLoading = { episodesViewModel.isLoading() },
                    onLoadMore = { episodesViewModel.loadNextPage() },
                    isLast = { episodesViewModel.isLastPage() })
                adapter = onBuildAdapter()
            }
            swipeRefreshLayout.configure { episodesViewModel.load() }
        }
        episodesViewModel.load()
    }

    override fun onEpisodeClicked(episode: Episode) {


    }

    /**
     * Private Methods
     */

    /**
     * On Build Adapter
     */
    private fun onBuildAdapter(): EpisodeListAdapter =
        EpisodeListAdapter(context = requireContext(), data = mutableListOf(), episodeItemListener = this)
}