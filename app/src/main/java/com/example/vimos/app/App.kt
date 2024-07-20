package com.example.vimos.app

import android.app.Application
import com.example.vimos.di.components.AppComponent

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this)
    }

    companion object {
        lateinit var appComponent: AppComponent
            private set
    }
}