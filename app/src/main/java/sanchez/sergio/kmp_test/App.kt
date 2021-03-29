package sanchez.sergio.kmp_test

import android.app.Application
import android.content.Context
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import sanchez.sergio.kmp_test.di.initKoin
import sanchez.sergio.kmp_test.ui.characters.CharacterDetailViewModel
import sanchez.sergio.kmp_test.ui.characters.CharactersViewModel
import sanchez.sergio.kmp_test.ui.episodes.EpisodesViewModel
import sanchez.sergio.kmp_test.ui.locations.LocationsViewModel

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        onConfigureDI()
    }

    private fun onConfigureDI() {
        initKoin(module {
            single<Context> { this@App }
            viewModel { CharactersViewModel(get()) }
            viewModel { CharacterDetailViewModel(get()) }
            viewModel { EpisodesViewModel() }
            viewModel { LocationsViewModel() }
        })
    }

}