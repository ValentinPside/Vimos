package com.example.vimos.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vimos.R
import com.example.vimos.domain.Repository
import com.example.vimos.domain.models.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class CatalogViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val state = MutableStateFlow(CatalogViewState())
    fun observeUi() = state.asStateFlow()

    fun getCatalog(title: String, slug: String) {
        viewModelScope.launch {
            state.update { it.copy(isLoading = true) }
            try {
                val catalogPreList = repository.getCategoryList(slug)
                val catalogList = mutableListOf<Product>()
                for (i in catalogPreList){
                    try {
                        val a = repository.getProduct(i.slug)
                        catalogList.add(a)
                    }catch (e: Exception){
                        state.update { it.copy(error = R.string.error_message_2) }
                    }
                }
                state.update {
                    it.copy(
                        title = title,
                        items = catalogList,
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
    val items: List<Product> = emptyList(),
    val error: Int? = null,
    val isLoading: Boolean = false
)