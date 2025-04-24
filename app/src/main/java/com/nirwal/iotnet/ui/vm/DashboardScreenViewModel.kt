package com.nirwal.iotnet.ui.vm

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.currentComposer
import androidx.lifecycle.ViewModel
import com.nirwal.iotnet.model.Device
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DashboardScreenViewModel: ViewModel() {


    // Backing property to avoid state updates from other classes
    private val _uiState = MutableStateFlow(DashboardUiState())
    val uiState: StateFlow<DashboardUiState> = _uiState.asStateFlow()

    fun onClickDeviceStartCmdBtn (start: Boolean) {
        Log.d("btn", "Buttan clicked state = $start ")
        updateDeviceOnOfState()
    }

    // update not working
    private fun updateDeviceOnOfState(){
        _uiState.update { currentState->
            currentState.devices[0].isOn = false
            currentState.copy(devices = currentState.devices)

        }

    }
}


data class DashboardUiState(
    var devices: List<Device> = listOf(
        Device("Water Pump", true, "Running"),
        Device("Thermostat", false, "22°C"),
        Device("Security Camera", true),
        Device("Humidity Sensor", true, "45%"),
    )
)
