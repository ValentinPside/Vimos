package com.example.vimos.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vimos.R
import com.example.vimos.domain.Repository
import com.example.vimos.domain.models.Slug
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class CatalogViewModel @Inject constructor(
    private val repository: Repository,
    title: String,
    slug: String
) : ViewModel() {

    private val state = MutableStateFlow(CatalogViewState())
    fun observeUi() = state.asStateFlow()

    init {
        getCatalog(title, slug)
    }

    private fun getCatalog(title: String, slug: String) {
        viewModelScope.launch {
            state.update { it.copy(isLoading = true) }
            try {
                val catalogPreList = repository.getCategoryList(slug)
                state.update {
                    it.copy(
                        title = title,
                        items = catalogPreList,
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
}

data class CatalogViewState(
    val title: String = "",
    val items: List<Slug> = emptyList(),
    val error: Int? = null,
    val isLoading: Boolean = false
)