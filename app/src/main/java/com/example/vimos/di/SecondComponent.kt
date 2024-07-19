package com.example.vimos.di

import com.example.vimos.domain.Categories
import com.example.vimos.presentation.viewmodels.SecondViewModel
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent
interface SecondComponent {

    fun viewModel(): SecondViewModel

    @Subcomponent.Factory
    interface SecondComponentFactory {
        fun create(@BindsInstance categories: Categories, @BindsInstance title: String): SecondComponent
    }

}