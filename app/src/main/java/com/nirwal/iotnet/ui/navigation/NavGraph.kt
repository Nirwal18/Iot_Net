package com.nirwal.iotnet.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nirwal.iotnet.ui.screen.DashboardScreen
import com.nirwal.iotnet.ui.screen.DeviceTemplateScreen
import com.nirwal.iotnet.ui.screen.HomeScreen
import com.nirwal.iotnet.ui.vm.DashboardScreenViewModel
import com.nirwal.iotnet.ui.vm.DeviceTemplateViewModel

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
            //val state = vm.uiState.collectAsState()

            DashboardScreen(
                state = vm.uiState.collectAsState().value,
                vm = vm,
                onClickDeviceStartCmdBtn = vm::onClickDeviceStartCmdBtn,
                navigateToDeviceTemplate = {
                    navController.navigate(Screen.DeviceTemplate.route)
                }
            )
        }

        composable(route = Screen.DeviceTemplate.route){
            val vm: DeviceTemplateViewModel = viewModel()
            DeviceTemplateScreen(
                state = vm.uiState.collectAsState().value,
                onClickOnCmd = vm::onClickOnCmd
            )
        }

    }
}

