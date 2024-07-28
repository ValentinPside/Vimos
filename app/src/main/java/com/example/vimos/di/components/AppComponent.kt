package com.example.vimos.di.components

import android.content.Context
import com.example.vimos.di.modules.NetworkModule
import com.example.vimos.di.modules.RepositoryModule
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

    fun secondComponent(): SecondComponent.SecondComponentFactory

    fun thirdComponent(): ThirdComponent.ThirdComponentFactory

    fun catalogComponent(): CatalogComponent

    fun productComponent(): ProductComponent.ProductComponentFactory

    @Component.Factory
    interface AppComponentFactory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}