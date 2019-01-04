package com.example.carlos.forecastapp.data.network.response

import com.example.carlos.forecastapp.data.db.entity.CurrentWeatherEntry
import com.example.carlos.forecastapp.data.db.entity.WeatherLocation
import com.google.gson.annotations.SerializedName

data class CurrentWeatherResponse(
    @SerializedName("current")
    val currentWeatherEntry: CurrentWeatherEntry,
    val location: WeatherLocation
)