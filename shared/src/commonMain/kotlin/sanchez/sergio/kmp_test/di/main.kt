package sanchez.sergio.kmp_test.di

import co.touchlab.kermit.Kermit
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.parameter.parametersOf
import sanchez.sergio.kmp_test.AppInfo
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
fun initKoin(appModule: Module): KoinApplication {

    val koinApplication = startKoin {
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

    val koin = koinApplication.koin
    val doOnStartup = koin.get<() -> Unit>() // doOnStartup is a lambda which is implemented in Swift on iOS side
    doOnStartup.invoke()

    val kermit = koin.get<Kermit> { parametersOf(null) }
    val appInfo = koin.get<AppInfo>() // AppInfo is a Kotlin interface with separate Android and iOS implementations
    kermit.v { "App Id ${appInfo.appId}" }

    return koinApplication
}
