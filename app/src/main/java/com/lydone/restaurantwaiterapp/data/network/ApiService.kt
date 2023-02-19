package com.lydone.restaurantwaiterapp.data.network

import com.lydone.restaurantwaiterapp.data.network.model.Event
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("api/event")
    suspend fun getEvents(@Body tables: List<Int>): List<Event>
}