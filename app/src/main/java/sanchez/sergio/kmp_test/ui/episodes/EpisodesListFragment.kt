package sanchez.sergio.kmp_test.ui.episodes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.KoinComponent
import sanchez.sergio.kmp_test.databinding.EpisodeListFragmentBinding
import sanchez.sergio.kmp_test.domain.models.Episode

/**
 * Episodes List Fragment
 */
class EpisodesListFragment: Fragment(), KoinComponent {

    private val episodesViewModel: EpisodesViewModel by viewModel()
    private val binding by lazy { EpisodeListFragmentBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launchWhenStarted {
            episodesViewModel.state.addObserver {
                when(it) {
                    is EpisodesState.OnLoading -> onLoading()
                    is EpisodesState.OnError -> onError(it.error)
                    is EpisodesState.OnSuccess -> onLoaded(it.episodeList)
                }
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        episodesViewModel.load()
    }

    /**
     * Private Methods
     */

    private fun onLoading() {
        Log.d("EPI", "onLoading CALLED")
    }

    private fun onLoaded(episodes: List<Episode>) {
        Log.d("EPI", "onLoaded ${episodes.size} CALLED")
    }

    private fun onError(ex: Exception) {
        ex.printStackTrace()
        Log.d("EPI", "onError ${ex.message} CALLED")
    }
}