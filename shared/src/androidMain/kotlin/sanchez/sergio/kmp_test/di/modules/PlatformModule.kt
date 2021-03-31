package sanchez.sergio.kmp_test.di.modules

import co.touchlab.kermit.Kermit
import co.touchlab.kermit.LogcatLogger
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import org.koin.core.module.Module
import org.koin.dsl.module
import sanchez.sergio.kmp_test.db.KmpArchDb

/**
 * Platform Module
 */
actual val platformModule: Module = module {

    single<SqlDriver> {
        AndroidSqliteDriver(KmpArchDb.Schema, get(), "KmpArchDb")
    }

    val baseKermit = Kermit(LogcatLogger()).withTag("KmpArch")

    factory { (tag: String?) ->
        if (tag != null)
            baseKermit.withTag(tag)
        else
            baseKermit
    }
}