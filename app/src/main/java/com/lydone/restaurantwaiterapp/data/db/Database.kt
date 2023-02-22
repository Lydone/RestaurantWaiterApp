package com.lydone.restaurantwaiterapp.data.db

import androidx.room.RoomDatabase
import com.lydone.restaurantwaiterapp.data.db.model.Event
import com.lydone.restaurantwaiterapp.data.db.model.Table

@androidx.room.Database(entities = [Event::class, Table::class], version = 1)
abstract class Database : RoomDatabase() {

    abstract fun eventDao(): EventDao

    abstract fun tableDao(): TableDao
}