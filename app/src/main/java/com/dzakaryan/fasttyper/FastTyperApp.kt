package com.dzakaryan.fasttyper

import android.app.Application
import com.facebook.stetho.Stetho

class FastTyperApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
    }
}