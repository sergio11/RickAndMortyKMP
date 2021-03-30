package sanchez.sergio.kmp_test.ui.episodes

import android.content.Context
import android.view.ViewGroup
import sanchez.sergio.kmp_test.databinding.EpisodeItemLayoutBinding
import sanchez.sergio.kmp_test.domain.models.Episode
import sanchez.sergio.kmp_test.ui.core.SupportAdapter

/**
* Episode List Adapter
* @param context
* @param data
* @param episodeItemListener
*/
class EpisodeListAdapter(
    context: Context,
    data: MutableList<Episode>,
    private val episodeItemListener: OnEpisodeClickListener,
): SupportAdapter<EpisodeListAdapter.EpisodeViewHolder, Episode>(context, data) {

    /**
     * Create View Holder
     * @param parent
     * @param viewType
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder =
        EpisodeViewHolder(EpisodeItemLayoutBinding.inflate(inflater, parent, false))


    interface OnEpisodeClickListener {

        /**
         * on Episode Clicked
         * @param episode
         */
        fun onEpisodeClicked(episode: Episode)
    }

    /**
     * Episode View Holder
     * @param binding
     */
    inner class EpisodeViewHolder(private val binding: EpisodeItemLayoutBinding):
        SupportAdapter.SupportViewHolder<Episode>(binding.root) {
        override fun bind(model: Episode) {
            with(binding) {
                episode = model
            }
        }
    }


}