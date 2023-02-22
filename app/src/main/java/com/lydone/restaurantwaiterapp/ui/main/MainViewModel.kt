package com.lydone.restaurantwaiterapp.ui.main

import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.lydone.restaurantwaiterapp.data.model.Event
import com.lydone.restaurantwaiterapp.data.repository.EventRepository
import com.lydone.restaurantwaiterapp.data.repository.TableRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    @ApplicationContext context: Context,
    private val eventRepository: EventRepository,
    private val tableRepository: TableRepository,
) : AndroidViewModel(context as android.app.Application) {

    val uiState = MutableStateFlow(State(emptyList(), false, emptyList()))

    init {
        viewModelScope.launch {
            launch {
                tableRepository.selectedTables.collect { tables ->
                    loadEvents(tables)
                    setNotifications(tables)
                }
            }
            launch {
                eventRepository.events.collect { events ->
                    uiState.update { it.copy(events = events) }
                }
            }
        }
    }

    fun loadEvents() = viewModelScope.launch {
        loadEvents(tableRepository.selectedTables.first())
    }

    private fun setNotifications(tables: List<Int>) = viewModelScope.launch {
        eventRepository.setNotifications(tables)
    }

    private suspend fun loadEvents(tables: List<Int>) {
        uiState.update { it.copy(loading = true, tables = tables) }
        eventRepository.loadEvents(tables)
        uiState.update { it.copy(loading = false) }
    }

    fun onDismiss(event: Event) = viewModelScope.launch { eventRepository.deleteEvent(event) }


    data class State(val events: List<Event>, val loading: Boolean, val tables: List<Int>)
}