package com.example.carlos.forecastapp.data.network

import androidx.lifecycle.LiveData
import com.example.carlos.forecastapp.data.network.response.CurrentWeatherResponse


interface WeatherNetworkDataSource {

    val downloadedCurrentWeather: LiveData<CurrentWeatherResponse>

    suspend fun fetchCurrentWeather(
        location: String
    )

}