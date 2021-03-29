package sanchez.sergio.kmp_test.di.modules

import co.touchlab.kermit.Kermit
import co.touchlab.kermit.LogcatLogger
import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * Platform Module
 */
actual val platformModule: Module = module {

    val baseKermit = Kermit(LogcatLogger()).withTag("KampKit")

    factory { (tag: String?) ->
        if (tag != null)
            baseKermit.withTag(tag)
        else
            baseKermit
    }
}