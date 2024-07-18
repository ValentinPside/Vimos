package com.example.vimos.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vimos.R
import com.example.vimos.domain.Repository
import com.example.vimos.domain.models.FirstLevelCategories
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class FirstViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val state = MutableStateFlow(ViewState())
    fun observeUi() = state.asStateFlow()

    init {
        getFirstLevelCategories()
    }

    fun getFirstLevelCategories() {
        viewModelScope.launch {
            state.update { it.copy(isLoading = true) }
            try {
                val list = repository.getZeroLevelList()
                state.update { it.copy(items = list[0].subCategories[0].subCategories, error = null) }
            } catch (e: Exception) {
                state.update { it.copy(error = R.string.error_message) }
            } finally {
                state.update { it.copy(isLoading = false) }
            }
        }
    }

}


data class ViewState(
    val items: List<FirstLevelCategories> = emptyList(),
    val error: Int? = null,
    val isLoading: Boolean = false
)