package com.myweatherapp.model

import kotlinx.serialization.Serializable

@Serializable
data class WeatherDataSys (
    // Country code (GB, JP etc.)
    val country: String,

    // Sunrise time, unix, UTC
    val sunrise: Long,

    // Sunset time, unix, UTC
    val sunset: Long,
)
