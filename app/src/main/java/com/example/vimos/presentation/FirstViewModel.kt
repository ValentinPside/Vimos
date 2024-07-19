package com.example.vimos.presentation

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

    private val state = MutableStateFlow(ViewState())
    fun observeUi() = state.asStateFlow()

    init {
        getZeroLevelCategories()
    }

    fun getZeroLevelCategories() {
        viewModelScope.launch {
            state.update { it.copy(isLoading = true) }
            try {
                val list = repository.getZeroLevelList()
                state.update { it.copy(items = list[0].subCategories, error = null) }
            } catch (e: Exception) {
                state.update { it.copy(error = R.string.error_message) }
            } finally {
                state.update { it.copy(isLoading = false) }
            }
        }
    }

    fun getFirstLevelCategories(title: String) {
        viewModelScope.launch {
            state.update { it.copy(isLoading = true) }
            try {
                val list = state.value.items
                state.update { it.copy(items = getListByTitle(title), error = null) }
            } catch (e: Exception) {
                state.update { it.copy(error = R.string.error_message) }
            } finally {
                state.update { it.copy(isLoading = false) }
            }
        }
    }

    fun getSecondLevelCategories(title: String) {
        viewModelScope.launch {
            state.update { it.copy(isLoading = true) }
            try {
                val list = state.value.items
                state.update { it.copy(items = getListByTitle(title), error = null) }
            } catch (e: Exception) {
                state.update { it.copy(error = R.string.error_message) }
            } finally {
                state.update { it.copy(isLoading = false) }
            }
        }
    }

    fun getThirdLevelCategories(title: String) {
        viewModelScope.launch {
            state.update { it.copy(isLoading = true) }
            try {
                val list = getListByTitle(title)
                state.update { it.copy(items = list, error = null) }
            } catch (e: Exception) {
                state.update { it.copy(error = R.string.error_message) }
            } finally {
                state.update { it.copy(isLoading = false) }
            }
        }
    }

    private fun getListByTitle(title: String): List<Categories>{
        var index = 0
        for(i in state.value.items){
            if (title == i.title){
                return i.subCategories
            } else index++
        }
        return state.value.items[index].subCategories
    }

}


data class ViewState(
    val headText: String = "",
    val items: List<Categories> = emptyList(),
    val error: Int? = null,
    val isLoading: Boolean = false
)