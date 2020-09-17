package com.dzakaryan.fasttyper

import android.app.Application
import com.dzakaryan.fasttyper.di.module.appModule
import com.dzakaryan.fasttyper.di.module.repositoryModule
import com.facebook.stetho.Stetho
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class FastTyperApp : Application() {

    //region Lifecycle methods
    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)

        // start Koin context
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@FastTyperApp)
            modules(appModule, repositoryModule)
        }
    }
    //endregion
}