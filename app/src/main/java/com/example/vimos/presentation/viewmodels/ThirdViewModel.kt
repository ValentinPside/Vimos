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

class ThirdViewModel @Inject constructor(
    categories: Categories,
    title: String,
    firstIndex: Int
) : ViewModel() {

    private val state = MutableStateFlow(ThirdViewState())
    fun observeUi() = state.asStateFlow()

    init {
        getSecondLevelCategories(categories, title, firstIndex)
    }

    private fun getSecondLevelCategories(categories: Categories, title: String, firstIndex: Int) {
        viewModelScope.launch {
            state.update { it.copy(isLoading = true) }
            try {
                val index = getIndexByTitle(title, categories.subCategories[firstIndex].subCategories)
                state.update {
                    it.copy(
                        title = title,
                        items = categories.subCategories[firstIndex].subCategories[index].subCategories,
                        error = null
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

data class ThirdViewState(
    val title: String = "",
    val items: List<Categories> = emptyList(),
    val error: Int? = null,
    val isLoading: Boolean = false
)