package com.lydone.restaurantwaiterapp.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lydone.restaurantwaiterapp.R
import com.lydone.restaurantwaiterapp.data.model.Event

@Composable
fun MainRoute(navigateToTablePicker: () -> Unit, viewModel: MainViewModel = hiltViewModel()) {
    MainScreen(
        viewModel.uiState.collectAsState().value,
        viewModel::onDismiss,
        viewModel::loadEvents,
        navigateToTablePicker
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun MainScreen(
    state: MainViewModel.State,
    onDismiss: (Event) -> Unit,
    onRefresh: () -> Unit,
    onTablePickerClick: () -> Unit,
) {
    val pullRefreshState =
        rememberPullRefreshState(refreshing = state.loading, onRefresh = onRefresh)
    Box {
        Column {
            TablePicker(onTablePickerClick, state)
            LazyColumn(
                modifier = Modifier
                    .pullRefresh(pullRefreshState)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(bottom = 100.dp),
            ) {
                state.events.forEach {
                    item(key = it.id) { EventCard(it, onDismiss = { onDismiss(it) }) }
                }
            }
        }
        PullRefreshIndicator(
            refreshing = state.loading,
            state = pullRefreshState,
            Modifier.align(Alignment.TopCenter)
        )
    }
}

@Composable
private fun TablePicker(
    onClick: () -> Unit,
    state: MainViewModel.State
) = Card(
    Modifier
        .padding(16.dp)
        .clickable(onClick = onClick),
    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
) {
    Row(Modifier.padding(16.dp)) {
        Text(
            if (state.tables.isEmpty()) {
                stringResource(R.string.choose_tables)
            } else {
                stringResource(
                    R.string.subscription_to_tables,
                    state.tables.joinToString(", ")
                )
            },
            Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Icon(
            painter = painterResource(R.drawable.baseline_chevron_right_24),
            contentDescription = null
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun EventCard(event: Event, onDismiss: () -> Unit) {
    val state = rememberDismissState()
    if (state.isDismissed(DismissDirection.EndToStart)) {
        onDismiss()
    } else {
        SwipeToDismiss(
            state = state,
            background = {},
            dismissContent = {
                OutlinedCard(
                    Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth()
                ) {
                    Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painterResource(
                                if (event.dish == null) {
                                    R.drawable.baseline_notifications_active_24
                                } else {
                                    R.drawable.baseline_fastfood_24
                                }
                            ),
                            contentDescription = null,
                            modifier = Modifier
                                .padding(end = 16.dp)
                                .background(MaterialTheme.colorScheme.primaryContainer, CircleShape)
                                .padding(8.dp),
                            tint = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                        Column {
                            Text(
                                if (event.dish == null) {
                                    stringResource(R.string.event_call)
                                } else {
                                    stringResource(R.string.event_dish, event.dish)
                                }
                            )
                            Text(stringResource(R.string.table, event.table))
                        }
                    }
                }
            },
            directions = setOf(DismissDirection.EndToStart)
        )
    }
}