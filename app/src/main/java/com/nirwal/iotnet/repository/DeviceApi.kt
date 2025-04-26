package com.nirwal.iotnet.repository

import android.util.Log
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode


class DeviceApi(){
    val deviceKey = "_uuXKKDmBALDD86gNbhtUanLnrEKH7EY"
    val BASE_URL = "https://blr1.blynk.cloud/"


    suspend fun getDeviceConnectionStatus(): Boolean{
        val url = BASE_URL+"external/api/isHardwareConnected?token=$deviceKey"
        var status = false
        try {
            val response = ApiClient.get(url)
            Log.d("Ktor", "Received: $response")
            if(response.status== HttpStatusCode.OK){
                status = response.body<Boolean>()
                Log.d("Ktor", "Device status: ${if(status) "Online" else "Offline"}");
            } else {
                Log.d("Ktor", "Failed to fetch device status.")
            }
        } catch (e: Exception) {
            Log.e("Ktor", "Error: ${e.localizedMessage}")

        }
        return status
    }

    suspend fun writeToVirtualPin(virtualPin: String, cmd: String){

        val url = BASE_URL+"external/api/update?token=$deviceKey&$virtualPin=$cmd"
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

    suspend fun readFromVirtualPin(virtualPin: String): String {
        var data = ""
        val url = BASE_URL+"external/api/get?token=$deviceKey&$virtualPin"
        try {
            val response = ApiClient.get(url)
            Log.d("Ktor", "Received: $response")
            if(response.status== HttpStatusCode.OK){
                data = response.body()
                Log.d("Ktor", "Pump start cmd send success")
            } else {
                Log.d("Ktor", "Pump start cmd send failed.")
            }
        } catch (e: Exception) {
            Log.e("Ktor", "Error: ${e.localizedMessage}")

        }
        return data
    }



}