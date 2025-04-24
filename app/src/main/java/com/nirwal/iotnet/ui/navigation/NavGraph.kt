package com.nirwal.iotnet.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nirwal.iotnet.ui.screen.DashboardScreen
import com.nirwal.iotnet.ui.screen.HomeScreen
import com.nirwal.iotnet.ui.vm.DashboardScreenViewModel

@Composable
fun SetupNavGraph(
    navController: NavHostController
){
    NavHost(
        navController = navController,
        startDestination = Screen.Dashboard.route
    ){
        composable(route = Screen.Home.route){
            HomeScreen()
        }

        composable(route = Screen.Dashboard.route){

            val vm: DashboardScreenViewModel = viewModel()

            DashboardScreen(
                state = vm.uiState.collectAsState().value,
                onClickDeviceStartCmdBtn = {vm.onClickDeviceStartCmdBtn(it)}
            )
        }

    }
}

