package com.myweatherapp.model

import kotlinx.serialization.Serializable

@Serializable
data class WeatherDataWeather (
    // Weather condition id
    val id: Int,

    // Group of weather parameters (Rain, Snow, Extreme etc.)
    val main: String,

    // Weather condition within the group
    val description: String,

    // Weather icon id
    val icon: String,
)
