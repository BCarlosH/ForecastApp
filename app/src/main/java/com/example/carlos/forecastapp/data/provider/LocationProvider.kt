package com.example.carlos.forecastapp.data.provider

import com.example.carlos.forecastapp.data.db.entity.WeatherLocation


interface LocationProvider {

    suspend fun hasLocationChanged(lastWeatherLocation: WeatherLocation): Boolean
    suspend fun getPreferredLocationString(): String

}