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

    private val state = MutableStateFlow(SecondViewState())
    fun observeUi() = state.asStateFlow()

    init {
        getFirstLevelCategories(categories, title)
    }

    private fun getFirstLevelCategories(categories: Categories, title: String) {
        viewModelScope.launch {
            state.update { it.copy(isLoading = true) }
            try {
                val index = getIndexByTitle(title, categories.subCategories)
                state.update {
                    it.copy(
                        title = title,
                        items = categories.subCategories[index].subCategories,
                        error = null,
                        index = getIndexByTitle(title, categories.subCategories)
                    )
                }
            } catch (e: Exception) {
                state.update { it.copy(error = R.string.error_message) }
            } finally {
                state.update { it.copy(isLoading = false) }
            }
        }
    }

    private fun getIndexByTitle(title: String, list: List<Categories>): Int {
        for (i in list) {
            if (title == i.title) {
                return list.indexOf(i)
            }
        }
        return 0
    }
}

data class SecondViewState(
    val title: String = "",
    val items: List<Categories> = emptyList(),
    val error: Int? = null,
    val isLoading: Boolean = false,
    val index: Int = 0
)