package sanchez.sergio.kmp_test.ui.characters

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import dev.icerock.moko.mvvm.MvvmFragment
import dev.icerock.moko.mvvm.createViewModelFactory
import org.koin.core.KoinComponent
import sanchez.sergio.kmp_test.R
import sanchez.sergio.kmp_test.databinding.CharacterListFragmentBinding
import sanchez.sergio.kmp_test.domain.models.Character
import java.lang.Exception

/**
 * Character List Fragment
 */
class CharacterListFragment: MvvmFragment<CharacterListFragmentBinding, CharactersViewModel>(),  KoinComponent {

    override val layoutId = R.layout.character_list_fragment
    override val viewModelClass: Class<CharactersViewModel> =
        CharactersViewModel::class.java
    override val viewModelVariableId = 1

    override fun viewModelFactory(): ViewModelProvider.Factory =
        createViewModelFactory { CharactersViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launchWhenStarted {
            viewModel.state.addObserver { state ->
                when(state) {
                    is CharactersState.OnLoading -> onLoading()
                    is CharactersState.OnError -> onError(state.error)
                    is CharactersState.OnSuccess -> onLoaded(state.characterList)
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.load()
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
        Log.d("CHAR", "onError CALLED")
    }


}