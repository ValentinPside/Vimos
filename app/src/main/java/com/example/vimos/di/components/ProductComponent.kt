package com.example.vimos.di.components

import com.example.vimos.presentation.viewmodels.ProductViewModel
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent
interface ProductComponent {

    fun viewModel(): ProductViewModel

    @Subcomponent.Factory
    interface ProductComponentFactory {
        fun create(
            @BindsInstance slug: String
        ): ProductComponent
    }

}