package com.example.vimos.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vimos.R
import com.example.vimos.domain.Categories
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class SecondViewModel @Inject constructor(
    categories: Categories,
    title: String
) : ViewModel() {

    private val state = MutableStateFlow(SpecificViewState())
    fun observeUi() = state.asStateFlow()

    init {
        getFirstLevelCategories(categories, title)
    }

    private fun getFirstLevelCategories(categories: Categories, title: String) {
        viewModelScope.launch {
            state.update { it.copy(isLoading = true) }
            try {
                state.update { it.copy(title = title, items = categories.subCategories[1].subCategories, error = null) }
            } catch (e: Exception) {
                state.update { it.copy(error = R.string.error_message) }
            } finally {
                state.update { it.copy(isLoading = false) }
            }
        }
    }
}

data class SpecificViewState(
    val title: String = "",
    val items: List<Categories> = emptyList(),
    val error: Int? = null,
    val isLoading: Boolean = false
)