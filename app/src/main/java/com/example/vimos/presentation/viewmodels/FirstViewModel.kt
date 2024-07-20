package com.example.vimos.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vimos.R
import com.example.vimos.domain.Categories
import com.example.vimos.domain.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class FirstViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val state = MutableStateFlow(FirstViewState())
    fun observeUi() = state.asStateFlow()

    init {
        getZeroLevelCategories()
    }

    private fun getZeroLevelCategories() {
        viewModelScope.launch {
            state.update { it.copy(isLoading = true) }
            try {
                val list = repository.getZeroLevelList()
                state.update {
                    it.copy(
                        items = list[0].subCategories,
                        error = null,
                        firstCategory = list[0]
                    )
                }
            } catch (e: Exception) {
                state.update { it.copy(error = R.string.error_message) }
            } finally {
                state.update { it.copy(isLoading = false) }
            }
        }
    }
}

data class FirstViewState(
    val title: String = "Строительные материалы",
    val items: List<Categories> = emptyList(),
    val error: Int? = null,
    val isLoading: Boolean = false,
    val firstCategory: Categories? = null
)