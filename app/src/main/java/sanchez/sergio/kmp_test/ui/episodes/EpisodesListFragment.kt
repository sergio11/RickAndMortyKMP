package sanchez.sergio.kmp_test.ui.episodes

import androidx.lifecycle.ViewModelProvider
import dev.icerock.moko.mvvm.MvvmFragment
import dev.icerock.moko.mvvm.createViewModelFactory
import sanchez.sergio.kmp_test.R
import sanchez.sergio.kmp_test.databinding.EpisodeListFragmentBinding

/**
 * Episodes List Fragment
 */
class EpisodesListFragment : MvvmFragment<EpisodeListFragmentBinding, EpisodesViewModel>() {

    override val layoutId: Int = R.layout.episode_list_fragment
    override val viewModelClass: Class<EpisodesViewModel> =
        EpisodesViewModel::class.java
    override val viewModelVariableId: Int = 332

    override fun viewModelFactory(): ViewModelProvider.Factory =
        createViewModelFactory { EpisodesViewModel() }
}