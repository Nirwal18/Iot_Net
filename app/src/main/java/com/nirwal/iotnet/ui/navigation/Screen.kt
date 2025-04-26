package com.nirwal.iotnet.ui.navigation

sealed class Screen(val route:String) {
    data object Home:Screen(route = "home_screen")
    data object Dashboard:Screen(route = "dashboard_screen")
    data object DeviceTemplate:Screen(route = "device_template_screen")
}