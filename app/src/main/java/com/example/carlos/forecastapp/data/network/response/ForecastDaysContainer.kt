package com.example.carlos.forecastapp.data.network.response

import com.example.carlos.forecastapp.data.db.entity.FutureWeatherEntry
import com.google.gson.annotations.SerializedName


data class ForecastDaysContainer(
    @SerializedName("forecastday")
    val entries: List<FutureWeatherEntry>
)