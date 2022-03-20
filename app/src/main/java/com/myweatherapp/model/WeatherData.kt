package com.myweatherapp.model

import kotlinx.serialization.Serializable

@Serializable
data class WeatherData(
    val coord: WeatherDataCoord,
    val weather: Array<WeatherDataWeather>,
    val main: WeatherDataMain,
    // Visibility, meter. The maximum value of the visibility is 10km
    val visibility: Int,
    val wind: WeatherDataWind,
    val clouds: WeatherDataClouds,
    // Time of data calculation, unix, UTC
    val dt: Long,
    val sys: WeatherDataSys,
    // Shift in seconds from UTC
    val timezone: Int,
    // City ID
    val id: Int,
    // City Name
    val name: String,
)
