package com.lydone.restaurantwaiterapp.ui.main

import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.lydone.restaurantwaiterapp.data.model.Event
import com.lydone.restaurantwaiterapp.data.repository.EventRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    @ApplicationContext context: Context,
    private val eventRepository: EventRepository
) : AndroidViewModel(context as android.app.Application) {

    val uiState = MutableStateFlow(State(emptyList(), false))

    init {
        viewModelScope.launch {
            eventRepository.events.collect { events ->
                uiState.update { it.copy(events = events) }
            }
        }
        getEvents()
    }

    fun getEvents() = viewModelScope.launch {
        uiState.update { it.copy(loading = true) }
        eventRepository.loadEvents(listOf(0))
        uiState.update { it.copy(loading = false) }
    }

    fun onDismiss(event: Event) = viewModelScope.launch { eventRepository.deleteEvent(event) }


    data class State(val events: List<Event>, val loading: Boolean)
}