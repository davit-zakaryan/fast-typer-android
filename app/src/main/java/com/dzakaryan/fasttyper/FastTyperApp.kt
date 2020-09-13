package com.dzakaryan.fasttyper

import android.app.Application
import com.dzakaryan.fasttyper.di.component.AppComponent
import com.dzakaryan.fasttyper.di.component.DaggerAppComponent
import com.dzakaryan.fasttyper.di.module.AppModule
import com.dzakaryan.fasttyper.di.module.NetworkModule
import com.facebook.stetho.Stetho

class FastTyperApp : Application() {

    //region Properties
    val applicationComponent: AppComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        initDagger(this)
    }
    //endregion

    //region Lifecycle methods
    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
    }
    //endregion

    //region Private utility methods
    private fun initDagger(application: FastTyperApp): AppComponent {
        return DaggerAppComponent
            .builder()
            .appModule(AppModule(application))
            .networkModule(NetworkModule())
            .build()
    }
    //endregion
}