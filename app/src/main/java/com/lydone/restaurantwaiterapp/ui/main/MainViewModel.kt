package com.lydone.restaurantwaiterapp.ui.main

import android.content.Context
import android.speech.tts.TextToSpeech
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.lydone.restaurantwaiterapp.Application
import com.lydone.restaurantwaiterapp.R
import com.lydone.restaurantwaiterapp.data.event.Event
import com.lydone.restaurantwaiterapp.data.event.EventRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    @ApplicationContext context: Context,
    private val eventRepository: EventRepository
) : AndroidViewModel(context as android.app.Application) {

    val state = eventRepository.events.stateIn(
        viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = emptyList()
    )

    val tts = TextToSpeech(getApplication(), {}).apply { language = Locale("ru") }

    fun addEvent() = viewModelScope.launch {
        val event = eventRepository.addEvent()
        val text = if (event.dish != null) {
            getApplication<Application>().getString(R.string.event_dish, event.dish)
        } else {
            getApplication<Application>().getString(R.string.event_call)
        } + getApplication<Application>().getString(R.string.event_table, event.table)
        tts.speak(text, TextToSpeech.QUEUE_ADD, null, null)
    }

    fun onDismiss(event: Event) = viewModelScope.launch { eventRepository.deleteEvent(event) }
}