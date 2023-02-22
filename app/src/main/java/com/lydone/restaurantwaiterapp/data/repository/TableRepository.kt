package com.lydone.restaurantwaiterapp.data.repository

import com.lydone.restaurantwaiterapp.data.db.TableDao
import com.lydone.restaurantwaiterapp.data.db.model.Table
import com.lydone.restaurantwaiterapp.data.network.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TableRepository @Inject constructor(
    private val apiService: ApiService,
    private val tableDao: TableDao,
) {

    val selectedTables = tableDao.getSelected().map { list -> list.map { it.id } }

    suspend fun getTablesWithSelections(): Flow<List<Pair<Int, Boolean>>> {
        val tables = apiService.getTables()
        return selectedTables.map { selectedTables ->
            tables.map { table -> table to selectedTables.contains(table) }
        }
    }

    suspend fun changeTableSelection(table: Int) {
        val tableEntity = Table(table)
        if (selectedTables.first().contains(table)) {
            tableDao.delete(tableEntity)
        } else {
            tableDao.insertAll(tableEntity)
        }
    }
}