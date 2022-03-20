package com.myweatherapp.model

import kotlinx.serialization.Serializable

@Serializable
data class WeatherDataCoord (
    // City geo location, longitude
    val lon: Float,

    // City geo location, latitude
    val lat: Float,
)
