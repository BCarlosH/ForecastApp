package com.example.carlos.forecastapp.data.network.response

import com.example.carlos.forecastapp.data.db.entity.WeatherLocation
import com.google.gson.annotations.SerializedName


data class FutureWeatherResponse(
    @SerializedName("forecast")
    val futureWeatherEntries: ForecastDaysContainer,
    val location: WeatherLocation
)