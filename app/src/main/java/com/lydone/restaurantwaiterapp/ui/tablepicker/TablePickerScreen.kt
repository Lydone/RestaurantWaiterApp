package com.lydone.restaurantwaiterapp.ui.tablepicker

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lydone.restaurantwaiterapp.R


@Composable
fun TablePickerRoute(viewModel: TablePickerViewModel = hiltViewModel()) {
    TablePickerScreen(
        tables = viewModel.uiState.collectAsState().value,
        onTableClick = viewModel::changeTableSelection,
    )
}

@Composable
private fun TablePickerScreen(
    tables: List<Pair<Int, Boolean>>?,
    onTableClick: (Int) -> Unit,
) {
    if (tables == null) {
        Box(Modifier.fillMaxSize()) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
    } else {
        LazyColumn {
            tables.forEach { (table, selected) ->
                item {
                    Column(Modifier.clickable { onTableClick(table) }) {
                        Row(
                            Modifier.padding(horizontal = 16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(stringResource(R.string.table, table), Modifier.weight(1f))
                            Checkbox(checked = selected, onCheckedChange = { onTableClick(table) })
                        }
                        Divider()
                    }
                }
            }
        }
    }
}