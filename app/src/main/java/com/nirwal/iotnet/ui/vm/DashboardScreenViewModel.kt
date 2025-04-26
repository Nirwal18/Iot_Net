package com.nirwal.iotnet.ui.vm

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nirwal.iotnet.model.Device
import com.nirwal.iotnet.repository.ApiClient
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DashboardScreenViewModel: ViewModel() {


    // Backing property to avoid state updates from other classes
    private val _uiState = MutableStateFlow(DashboardUiState())
    val uiState: StateFlow<DashboardUiState> = _uiState.asStateFlow()

    private val _list = mutableStateListOf<Device>(
        Device("Water Pump", true, "Running"),
        Device("Thermostat", false, "22°C"),
        Device("Security Camera", true),
        Device("Humidity Sensor", true, "45%")
    )



    var isStarted:Int  = 0


    fun fetchData() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoading = true)
            }
            delay(2000L)
            _uiState.update {
                it.copy(isLoading = false, isData = true)
            }
        }
    }



    fun onClickDeviceStartCmdBtn (start: Boolean) {


        Log.d("btn", "Button clicked state = $start ")
        sendCmdToServer(
            if(isStarted==0){
                isStarted++
            }else{
                isStarted--
            }
        )



    }


    fun sendCmdToServer(cmd: Int) {


        val url = "https://blr1.blynk.cloud/external/api/update?token=_uuXKKDmBALDD86gNbhtUanLnrEKH7EY&V10=$cmd"
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = ApiClient.get(url)
                Log.d("Ktor", "Received: $response")
                if(response.status== HttpStatusCode.OK){
                    Log.d("Ktor", "Pump start cmd send success")
                } else {
                    Log.d("Ktor", "Pump start cmd send failed.")
                }
            } catch (e: Exception) {
                Log.e("Ktor", "Error: ${e.localizedMessage}")
            }
        }
    }
}


data class DashboardUiState(
    val isLoading: Boolean = false,
    val isData: Boolean = false,
    val error: String? = "",
    val devices: SnapshotStateList<Device> = mutableStateListOf<Device>().apply {
        add(Device("Water Pump", true, "Running"))
        add(Device("Thermostat", false, "22°C"))
        add(Device("Security Camera", true))
        add(Device("Humidity Sensor", true, "45%"))

    }

)
