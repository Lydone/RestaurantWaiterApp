package com.lydone.restaurantwaiterapp.ui.tablepicker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lydone.restaurantwaiterapp.data.repository.TableRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TablePickerViewModel @Inject constructor(private val repository: TableRepository) :
    ViewModel() {

    private val _uiState = MutableStateFlow<List<Pair<Int, Boolean>>?>(null)
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getTablesWithSelections().collect {
                _uiState.value = it
            }
        }
    }

    fun changeTableSelection(table: Int) = viewModelScope.launch {
        repository.changeTableSelection(table)
    }
}