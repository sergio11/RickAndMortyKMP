package sanchez.sergio.kmp_test.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.NavigationUI
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.KoinComponent

import sanchez.sergio.kmp_test.R
import sanchez.sergio.kmp_test.core.extension.navController
import sanchez.sergio.kmp_test.databinding.ActivityMainBinding
import sanchez.sergio.kmp_test.ui.characters.CharactersViewModel

/**
 * Main Activity
 */
class MainActivity : AppCompatActivity(), KoinComponent {

    private val charactersViewModel: CharactersViewModel by viewModel()

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding) {
            navController(R.id.mainNavHostContainer)?.let { navCon ->
                NavigationUI.setupWithNavController(mainBottomNavigation, navCon)
            }
        }
    }
}
