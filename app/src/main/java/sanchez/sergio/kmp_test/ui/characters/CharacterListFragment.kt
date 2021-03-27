package sanchez.sergio.kmp_test.ui.characters

import androidx.lifecycle.ViewModelProvider
import dev.icerock.moko.mvvm.MvvmFragment
import dev.icerock.moko.mvvm.createViewModelFactory
import sanchez.sergio.kmp_test.R
import sanchez.sergio.kmp_test.databinding.CharacterListFragmentBinding

class CharacterListFragment: MvvmFragment<CharacterListFragmentBinding, CharactersViewModel>() {

    override val layoutId = R.layout.character_list_fragment
    override val viewModelClass: Class<CharactersViewModel> =
        CharactersViewModel::class.java
    override val viewModelVariableId = 1

    override fun viewModelFactory(): ViewModelProvider.Factory {
        return createViewModelFactory { CharactersViewModel() }
    }
}