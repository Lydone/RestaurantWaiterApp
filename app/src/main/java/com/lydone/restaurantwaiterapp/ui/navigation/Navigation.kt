package com.lydone.restaurantwaiterapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.lydone.restaurantwaiterapp.ui.main.MainRoute
import com.lydone.restaurantwaiterapp.ui.tablepicker.TablePickerRoute


const val MAIN_ROUTE = "main_route"
const val TABLE_PICKER_ROUTE = "table_picker"

@Composable
fun RestaurantWaiterNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController = navController, startDestination = MAIN_ROUTE, modifier = modifier) {
        composable(MAIN_ROUTE) {
            MainRoute(navigateToTablePicker = { navController.navigate(TABLE_PICKER_ROUTE) })
        }
        composable(TABLE_PICKER_ROUTE) {
            TablePickerRoute()
        }
    }
}