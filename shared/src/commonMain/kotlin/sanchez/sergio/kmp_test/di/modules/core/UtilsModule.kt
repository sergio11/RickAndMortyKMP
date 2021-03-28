package sanchez.sergio.kmp_test.di.modules.core

import co.touchlab.kermit.CommonLogger
import co.touchlab.kermit.Kermit
import org.koin.dsl.module

internal val utilsModule = module {
    single { Kermit(CommonLogger()) }

}