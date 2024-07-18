package com.example.vimos.di

import com.example.vimos.presentation.FirstViewModel
import dagger.Subcomponent

@Subcomponent
interface FirstComponent {

    fun viewModel(): FirstViewModel

}