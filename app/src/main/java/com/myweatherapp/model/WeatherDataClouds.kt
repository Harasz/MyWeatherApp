package com.myweatherapp.model

import kotlinx.serialization.Serializable

@Serializable
data class WeatherDataClouds (
    // Cloudiness, %
    val all: Int
)
