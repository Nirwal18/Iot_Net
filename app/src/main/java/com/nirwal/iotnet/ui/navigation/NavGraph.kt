package com.nirwal.iotnet.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nirwal.iotnet.ui.screen.DashboardScreen
import com.nirwal.iotnet.ui.screen.HomeScreen

@Composable
fun SetupNavGraph(
    navController: NavHostController
){
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ){
        composable(route = Screen.Home.route){
            HomeScreen()
        }

        composable(route = Screen.Dashboard.route){
            DashboardScreen()
        }

    }
}