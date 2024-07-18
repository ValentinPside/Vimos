package com.example.vimos.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkModule::class,
        RepositoryModule::class
    ]
)
interface AppComponent {

    fun firstComponent(): FirstComponent

    @Component.Factory
    interface AppComponentFactory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}