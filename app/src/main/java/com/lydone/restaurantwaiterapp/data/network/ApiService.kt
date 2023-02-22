package com.lydone.restaurantwaiterapp.data.network

import com.lydone.restaurantwaiterapp.data.network.model.Event
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @POST("api/event")
    suspend fun getEvents(@Body tables: List<Int>): List<Event>

    @DELETE("api/event/{id}")
    suspend fun deleteEvent(@Path("id") id: Int)

    @GET("api/tables")
    suspend fun getTables(): List<Int>

    @POST("api/set_notifications/{token}")
    suspend fun setNotifications(@Path("token") token: String, @Body tables: List<Int>)
}