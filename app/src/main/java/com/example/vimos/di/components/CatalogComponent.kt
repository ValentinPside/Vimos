package com.example.vimos.di.components

import com.example.vimos.presentation.viewmodels.CatalogViewModel
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent
interface CatalogComponent {

    fun viewModel(): CatalogViewModel

}