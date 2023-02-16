package com.lydone.restaurantwaiterapp.di

import android.content.Context
import androidx.room.Room
import com.lydone.restaurantwaiterapp.data.db.Database
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@dagger.Module
@InstallIn(SingletonComponent::class)
object Module {

    @Singleton
    @Provides
    fun provideDb(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, Database::class.java, "db").build()

    @Provides
    fun provideEventDao(database: Database) = database.eventDao()
}