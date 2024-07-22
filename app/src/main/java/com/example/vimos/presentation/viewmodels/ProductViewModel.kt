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

class ProductViewModel @Inject constructor(
    private val repository: Repository,
    slug: String
) : ViewModel() {

    private val state = MutableStateFlow(ProductViewState())
    fun observeUi() = state.asStateFlow()

    init {
        getProduct(slug)
    }

    private fun getProduct(slug: String) {
        viewModelScope.launch {
            state.update { it.copy(isLoading = true) }
            try {
                val product = repository.getProduct(slug)
                state.update {
                    it.copy(
                        product = product,
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

data class ProductViewState(
    val product: Product? = null,
    val error: Int? = null,
    val isLoading: Boolean = false
)