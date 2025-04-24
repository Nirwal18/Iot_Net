package com.nirwal.iotnet.model

data class Device(
    val name: String,
    var isOn: Boolean,
    val value: String? = null
)
