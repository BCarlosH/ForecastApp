package com.example.carlos.forecastapp.data.network

import androidx.lifecycle.LiveData
import com.example.carlos.forecastapp.data.network.response.CurrentWeatherResponse
import com.example.carlos.forecastapp.data.network.response.FutureWeatherResponse


interface WeatherNetworkDataSource {

    val downloadedCurrentWeather: LiveData<CurrentWeatherResponse>
    val downloadedFutureWeather: LiveData<FutureWeatherResponse>


    suspend fun fetchCurrentWeather(
        location: String
    )

    suspend fun fetchFutureWeather(
        location: String
    )

}