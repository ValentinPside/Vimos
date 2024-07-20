package com.example.vimos.di.components

import com.example.vimos.domain.Categories
import com.example.vimos.presentation.viewmodels.ThirdViewModel
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent
interface ThirdComponent {

    fun viewModel(): ThirdViewModel

    @Subcomponent.Factory
    interface ThirdComponentFactory {
        fun create(
            @BindsInstance categories: Categories,
            @BindsInstance title: String,
            @BindsInstance index: Int
        ): ThirdComponent
    }

}