package com.nirwal.iotnet.ui.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import com.nirwal.iotnet.repository.ApiClient
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DeviceTemplateViewModel: ViewModel() {

    // Backing property to avoid state updates from other classes
    private val _uiState = MutableStateFlow(DeviceState())
    val uiState: StateFlow<DeviceState> = _uiState.asStateFlow()

    fun fetchData(){
    TODO()
    }

    fun onClickOnCmd(isOn: Boolean){
        sendCmdToServer(if(isOn) 1 else 0);
    }

    fun sendCmdToServer(cmd: Int) {
        val url = "https://blr1.blynk.cloud/external/api/update?token=_uuXKKDmBALDD86gNbhtUanLnrEKH7EY&V0=$cmd"
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
                _uiState.update {
                    it.copy(error = e.localizedMessage?.toString()?: "Error in sending command.")
                }
            }
        }
    }
}

data class DeviceState(
    var isOn: Boolean = false,
    var isLoading: Boolean = false,
    var error: String? = null,
    var isConnected: Boolean = false,


    )