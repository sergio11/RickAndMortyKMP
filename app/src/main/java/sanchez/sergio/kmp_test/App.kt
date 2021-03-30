package sanchez.sergio.kmp_test

import android.app.Application
import android.content.Context
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module
import sanchez.sergio.kmp_test.di.initKoin
import sanchez.sergio.kmp_test.ui.characters.CharacterDetailViewModel
import sanchez.sergio.kmp_test.ui.characters.CharactersViewModel
import sanchez.sergio.kmp_test.ui.episodes.EpisodeDetailViewModel
import sanchez.sergio.kmp_test.ui.episodes.EpisodesViewModel
import sanchez.sergio.kmp_test.ui.locations.LocationDetailViewModel
import sanchez.sergio.kmp_test.ui.locations.LocationsViewModel

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        onConfigureDI()
    }

    private fun onConfigureDI() {
        initKoin(module {
            single<Context> { this@App }
            single<AppInfo> { AndroidAppInfo }
            viewModel { CharactersViewModel(get(), get { parametersOf("CharactersViewModel") }) }
            viewModel { CharacterDetailViewModel(get()) }
            viewModel { EpisodesViewModel(get()) }
            viewModel { EpisodeDetailViewModel(get()) }
            viewModel { LocationsViewModel(get()) }
            viewModel { LocationDetailViewModel(get()) }
        })
    }

}

object AndroidAppInfo : AppInfo {
    override val appId: String = BuildConfig.APPLICATION_ID
}


