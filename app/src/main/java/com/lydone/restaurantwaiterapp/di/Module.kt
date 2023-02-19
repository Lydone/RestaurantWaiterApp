package com.lydone.restaurantwaiterapp.di

import android.content.Context
import androidx.room.Room
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.lydone.restaurantwaiterapp.data.db.Database
import com.lydone.restaurantwaiterapp.data.network.ApiService
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@dagger.Module
@InstallIn(SingletonComponent::class)
object Module {

    @Singleton
    @Provides
    fun provideDb(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, Database::class.java, "db").fallbackToDestructiveMigration().build()

    @Provides
    fun provideEventDao(database: Database) = database.eventDao()

    @Provides
    fun provideApiService() = Retrofit.Builder()
        .baseUrl("http://192.168.31.70:8080")
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .build()
        .create(ApiService::class.java)
}