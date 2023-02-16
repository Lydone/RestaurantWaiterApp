package com.lydone.restaurantwaiterapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.lydone.restaurantwaiterapp.ui.main.MainRoute


const val MAIN_ROUTE = "main_route"
@Composable
fun RestaurantWaiterNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController = navController, startDestination = MAIN_ROUTE, modifier = modifier) {
        composable(MAIN_ROUTE) {
            MainRoute()
        }
    }
}