package com.lydone.restaurantwaiterapp.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lydone.restaurantwaiterapp.data.db.model.Table
import kotlinx.coroutines.flow.Flow

@Dao
interface TableDao {

    @Query("SELECT * FROM `table`")
    fun getSelected(): Flow<List<Table>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg tables: Table)

    @Delete
    suspend fun delete(table: Table)

}