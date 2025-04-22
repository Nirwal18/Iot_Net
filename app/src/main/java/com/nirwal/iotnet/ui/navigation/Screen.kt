package com.nirwal.iotnet.ui.navigation

sealed class Screen(val route:String) {
    data object Home:Screen(route = "home_screen")
    data object Dashboard:Screen(route = "dashbord_screen")
}