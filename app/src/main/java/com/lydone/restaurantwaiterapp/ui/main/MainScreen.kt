package com.lydone.restaurantwaiterapp.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lydone.restaurantwaiterapp.R
import com.lydone.restaurantwaiterapp.data.event.Event
import com.lydone.restaurantwaiterapp.ui.theme.RestaurantWaiterAppTheme

@Composable
fun MainRoute(viewModel: MainViewModel = hiltViewModel()) {
    MainScreen(viewModel.state.collectAsState().value, viewModel::onDismiss)
}

@Composable
private fun MainScreen(state: List<Event>, onDismiss: (Event) -> Unit) {
    Column {
        // TODO table picker
        Text(
            "TODO: Подписка на столы: 1, 2, 3, 4",
            Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    MaterialTheme.shapes.small
                )
                .padding(16.dp)

        )
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(bottom = 100.dp)
        ) {
            state.forEach { item(key = it.id) { EventCard(it, onDismiss = { onDismiss(it) }) } }
        }
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
                            Text(stringResource(R.string.event_table, event.table))
                        }
                    }
                }
            },
            directions = setOf(DismissDirection.EndToStart)
        )
    }
}

@Preview
@Composable
private fun MainScreen() {
    RestaurantWaiterAppTheme {
        MainScreen(listOf(Event(4, "Цезарь")), {})
    }
}