package com.lydone.restaurantwaiterapp.data.event

import kotlinx.coroutines.delay
import javax.inject.Inject
import kotlin.random.Random

class EventRepository @Inject constructor(private val eventDao: EventDao) {

    val events = eventDao.getAll()

    suspend fun addEvent(): Event {
        delay(2000)
        return Event(Random.nextInt(1, 5), if (Random.nextBoolean()) "Цезарь" else null).also {
            eventDao.insertAll(it)
        }
    }

    suspend fun deleteEvent(event: Event) = eventDao.delete(event)
}