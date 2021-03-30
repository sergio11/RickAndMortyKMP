package sanchez.sergio.kmp_test.ui.characters

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import sanchez.sergio.kmp_test.core.extension.configure
import sanchez.sergio.kmp_test.databinding.CharacterListFragmentBinding
import sanchez.sergio.kmp_test.domain.models.Character

/**
 * Character List Fragment
 */
class CharacterListFragment: Fragment(), CharacterListAdapter.OnCharacterClickListener {

    /**
     * Dependencies
     */
    private val characterViewModel: CharactersViewModel by viewModel()

    private val binding by lazy { CharacterListFragmentBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launchWhenStarted {
            characterViewModel.state.addObserver { state ->
                with(binding) {
                    Log.d("CHAR", "ui state -> $state CALLED")
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
                    isLoading = { characterViewModel.isLoading() },
                    onLoadMore = {},
                    isLast = {true})
                adapter = onBuildAdapter()
            }
            swipeRefreshLayout.configure { characterViewModel.load() }
        }

        characterViewModel.load()
    }

    /**
     * On Character Clicked
     * @param character
     */
    override fun onCharacterClicked(character: Character) {


    }

    /**
     * Private Methods
     */

    /**
     * On Build Adapter
     */
    private fun onBuildAdapter(): CharacterListAdapter =
        CharacterListAdapter(context = requireContext(), data = mutableListOf(), characterItemListener = this)

}