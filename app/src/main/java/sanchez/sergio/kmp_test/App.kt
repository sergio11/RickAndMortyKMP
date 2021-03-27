package sanchez.sergio.kmp_test

import android.app.Application
import android.content.Context
import org.koin.dsl.module
import sanchez.sergio.kmp_test.di.initKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        onConfigureDI()
    }

    private fun onConfigureDI() {
        initKoin(module {
            single<Context> { this@App }
        })
    }

}