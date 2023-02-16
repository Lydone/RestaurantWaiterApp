package com.lydone.restaurantwaiterapp.data.event

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity()
data class Event(
    val table: Int,
    val dish: String?,
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
)