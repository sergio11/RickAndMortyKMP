package sanchez.sergio.kmp_test.ui.episodes

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.KoinComponent
import sanchez.sergio.kmp_test.R
import sanchez.sergio.kmp_test.databinding.EpisodeListFragmentBinding
import sanchez.sergio.kmp_test.domain.models.Episode
import sanchez.sergio.kmp_test.ui.core.SupportRecyclerViewPagination

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
                addItemDecoration(object: RecyclerView.ItemDecoration() {
                    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
                        super.getItemOffsets(outRect, view, parent, state)
                        val commonPadding = requireContext().resources.getDimension(R.dimen.common_small_padding).toInt()
                        outRect.apply {
                            left = commonPadding
                            right = commonPadding
                            top = commonPadding
                            bottom = commonPadding
                        }
                    }
                })

                SupportRecyclerViewPagination(
                    recyclerView = this,
                    isLoading = { episodesViewModel.isLoading() },
                    loadMore = {  },
                    onLast = { true }
                )

                adapter = onBuildAdapter()
            }

            swipeRefreshLayout.apply {
                setColorSchemeColors(ContextCompat.getColor(requireContext(), R.color.colorPrimaryDark))
                setProgressBackgroundColorSchemeColor(ContextCompat.getColor(requireContext(), android.R.color.black))
                setOnRefreshListener {
                    episodesViewModel.load()
                }
            }
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