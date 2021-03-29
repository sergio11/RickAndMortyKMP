package sanchez.sergio.kmp_test.ui.characters

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import sanchez.sergio.kmp_test.databinding.CharacterListFragmentBinding
import sanchez.sergio.kmp_test.domain.models.Character

/**
 * Character List Fragment
 */
class CharacterListFragment: Fragment() {

    /**
     * Dependencies
     */
    private val characterViewModel: CharactersViewModel by viewModel()

    private val binding by lazy { CharacterListFragmentBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launchWhenStarted {
            characterViewModel.state.addObserver {
                when(it) {
                    is CharactersState.OnLoading -> onLoading()
                    is CharactersState.OnError -> onError(it.error)
                    is CharactersState.OnSuccess -> onLoaded(it.characterList)
                }
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        characterViewModel.load()
    }

    /**
     * Private Methods
     */

    private fun onLoading() {
        Log.d("CHAR", "onLoading CALLED")
    }

    private fun onLoaded(characters: List<Character>) {
        Log.d("CHAR", "onLoaded ${characters.size} CALLED")
    }

    private fun onError(ex: Exception) {
        ex.printStackTrace()
        Log.d("CHAR", "onError ${ex.message} CALLED")
    }


}