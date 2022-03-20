package com.myweatherapp.model

import kotlinx.serialization.Serializable

@Serializable
data class WeatherDataWind(
    // Wind speed. Unit: meter/sec.
    val speed: Float,

    // Wind direction, degrees (meteorological)
    val deg: Int,
)
