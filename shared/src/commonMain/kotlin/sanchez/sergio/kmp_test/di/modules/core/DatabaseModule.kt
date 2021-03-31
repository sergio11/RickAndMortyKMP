package sanchez.sergio.kmp_test.di.modules.core

import org.koin.dsl.module
import sanchez.sergio.kmp_test.db.KmpArchDb

/**
 * Database Module
 */
internal val databaseModule = module {

    factory { KmpArchDb(get()) }

}