package com.lydone.restaurantwaiterapp.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.lydone.restaurantwaiterapp.R
import com.lydone.restaurantwaiterapp.ui.main.MainViewModel
import com.lydone.restaurantwaiterapp.ui.navigation.MAIN_ROUTE
import com.lydone.restaurantwaiterapp.ui.navigation.RestaurantWaiterNavHost
import com.lydone.restaurantwaiterapp.ui.navigation.TABLE_PICKER_ROUTE

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RestaurantWaiterApp() {
    val navController = rememberNavController()
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        when (currentRoute) {
                            MAIN_ROUTE -> stringResource(R.string.title_main)
                            TABLE_PICKER_ROUTE -> stringResource(R.string.title_table_picker)
                            else -> ""
                        }
                    )
                },
                navigationIcon = {
                    if (currentRoute != MAIN_ROUTE) {
                        IconButton(onClick = navController::popBackStack) {
                            Icon(
                                painterResource(R.drawable.baseline_arrow_back_24),
                                contentDescription = null
                            )
                        }
                    }
                },
            )
        },
        floatingActionButton = {
            if (currentRoute == MAIN_ROUTE) {
                FloatingActionButton(
                    onClick = { TODO() },
                ) {
                    Icon(painterResource(R.drawable.baseline_edit_24), contentDescription = null)
                }
            }
        }
    ) { RestaurantWaiterNavHost(navController, Modifier.padding(it)) }
}