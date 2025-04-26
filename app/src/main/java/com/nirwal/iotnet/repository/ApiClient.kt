package com.nirwal.iotnet.repository

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.serialization.kotlinx.json.json
import io.ktor.websocket.WebSocketDeflateExtension.Companion.install
import kotlinx.serialization.json.Json


val ApiClient = HttpClient(CIO) {
    install(ContentNegotiation) {
        json(Json {
            ignoreUnknownKeys = true
            prettyPrint = true
            isLenient = true
        })
    }
    defaultRequest {

    }
}

const val deviceKey: String = ""
//https://{server_address}/external/api/isHardwareConnected?token={token}