package sanchez.sergio.kmp_test.di.modules

import co.touchlab.kermit.Kermit
import co.touchlab.kermit.NSLogLogger
import org.koin.dsl.module

actual val platformModule = module {

    val baseKermit = Kermit(NSLogLogger()).withTag("KampKit")
    factory { (tag: String?) ->
        if (tag != null)
            baseKermit.withTag(tag)
        else
            baseKermit
    }
}