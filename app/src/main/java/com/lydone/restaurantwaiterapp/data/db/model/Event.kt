package com.lydone.restaurantwaiterapp.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Event(
    @PrimaryKey val id: Int,
    val table: Int,
    val dish: String?,
)
