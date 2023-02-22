package com.lydone.restaurantwaiterapp.data.repository

import com.google.firebase.messaging.FirebaseMessaging
import com.lydone.restaurantwaiterapp.data.db.EventDao
import com.lydone.restaurantwaiterapp.data.model.Event
import com.lydone.restaurantwaiterapp.data.network.ApiService
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import com.lydone.restaurantwaiterapp.data.db.model.Event as EventDb

class EventRepository @Inject constructor(
    private val apiService: ApiService,
    private val eventDao: EventDao,
) {

    val events = eventDao.getAll().map { events -> events.map { Event(it.id, it.table, it.dish) } }

    suspend fun loadEvents(tables: List<Int>) {
        eventDao.deleteAll()
        eventDao.insertAll(apiService.getEvents(tables).map { EventDb(it.id, it.table, it.dish) })
    }

    suspend fun deleteEvent(event: Event) {
        eventDao.delete(EventDb(event.id, event.table, event.dish))
        apiService.deleteEvent(event.id)
    }

    suspend fun setNotifications(tables: List<Int>) {
        val token = suspendCoroutine<String> { continuation ->
            FirebaseMessaging.getInstance().token.addOnCompleteListener {
                continuation.resume(it.result)
            }
        }
        apiService.setNotifications(token, tables)
    }
}