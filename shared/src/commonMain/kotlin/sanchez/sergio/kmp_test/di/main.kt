package sanchez.sergio.kmp_test.di

import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import sanchez.sergio.kmp_test.di.modules.characters.charactersModule
import sanchez.sergio.kmp_test.di.modules.core.networkModule
import sanchez.sergio.kmp_test.di.modules.core.utilsModule
import sanchez.sergio.kmp_test.di.modules.episodes.episodesModule
import sanchez.sergio.kmp_test.di.modules.locations.locationsModule
import sanchez.sergio.kmp_test.di.modules.platformModule

/**
 * Init Koin
 * @param appModule
 */
fun initKoin(appModule: Module): KoinApplication =
    startKoin {
        modules(
            utilsModule,
            networkModule,
            *charactersModule,
            *episodesModule,
            *locationsModule,
            platformModule,
            appModule,
        )
    }