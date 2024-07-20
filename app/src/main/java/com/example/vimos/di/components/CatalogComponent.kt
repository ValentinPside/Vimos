package com.example.vimos.di.components

import com.example.vimos.presentation.viewmodels.CatalogViewModel
import dagger.BindsInstance
import dagger.Subcomponent

interface CatalogComponent {

    fun viewModel(): CatalogViewModel

    @Subcomponent.Factory
    interface CatalogComponentFactory {
        fun create(
            @BindsInstance title: String,
            @BindsInstance slug: String
        ): CatalogComponent
    }

}