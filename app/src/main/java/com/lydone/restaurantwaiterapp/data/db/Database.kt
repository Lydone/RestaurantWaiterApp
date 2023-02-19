package com.lydone.restaurantwaiterapp.data.db

import androidx.room.RoomDatabase
import com.lydone.restaurantwaiterapp.data.db.model.Event

@androidx.room.Database(entities = [Event::class], version = 1)
abstract class Database : RoomDatabase() {

    abstract fun eventDao(): EventDao
}