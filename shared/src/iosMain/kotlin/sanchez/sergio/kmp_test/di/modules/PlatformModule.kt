package sanchez.sergio.kmp_test.di.modules

import co.touchlab.kermit.Kermit
import co.touchlab.kermit.NSLogLogger
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import org.koin.dsl.module
import sanchez.sergio.kmp_test.db.KmpArchDb

actual val platformModule = module {

    single<SqlDriver> { NativeSqliteDriver(KmpArchDb.Schema, "KmpArchDb") }

    val baseKermit = Kermit(NSLogLogger()).withTag("KmpArch")
    factory { (tag: String?) ->
        if (tag != null)
            baseKermit.withTag(tag)
        else
            baseKermit
    }
}