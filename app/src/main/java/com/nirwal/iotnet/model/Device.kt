package com.nirwal.iotnet.model

data class Device(
    val name: String,
    val isOn: Boolean,
    val value: String? = null
)
