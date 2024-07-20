package com.example.vimos.di.modules

import com.example.vimos.data.RepositoryImpl
import com.example.vimos.domain.Repository
import dagger.Module
import dagger.Provides

@Module
object RepositoryModule {

    @Provides
    fun provideRepository(impl: RepositoryImpl): Repository = impl

}