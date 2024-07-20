package com.example.vimos.di.components

import com.example.vimos.presentation.viewmodels.FirstViewModel
import dagger.Subcomponent

@Subcomponent
interface FirstComponent {

    fun viewModel(): FirstViewModel

}