package com.myweatherapp.model

import kotlinx.serialization.Serializable

@Serializable
data class WeatherDataMain (
    // Temperature. Unit: Celsius
    val temp: Float,

    // Temperature. This temperature parameter accounts for the human perception of weather. Unit: Celsius.
    val feels_like: Float,

    // Minimum temperature at the moment. This is minimal currently observed temperature (within large megalopolises and urban areas). Unit: Celsius.
    val temp_min: Float,

    // Maximum temperature at the moment. This is maximal currently observed temperature (within large megalopolises and urban areas). Unit: Celsius.
    val temp_max: Float,

    // Atmospheric pressure (on the sea level, if there is no sea_level or grnd_level data), hPa
    val pressure: Int,

    //  Humidity, %
    val humidity: Int,
)
