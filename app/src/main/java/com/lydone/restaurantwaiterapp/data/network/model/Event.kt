package com.lydone.restaurantwaiterapp.data.network.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
data class Event(
    val id: Int,
    val table: Int,
    val dish: String?,
)