package sanchez.sergio.kmp_test.di

import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import sanchez.sergio.kmp_test.di.modules.networkModule

/**
 * Init Koin
 * @param appModule
 */
fun initKoin(appModule: Module): KoinApplication =
    startKoin {
        modules(
            networkModule,
            appModule,
        )
    }