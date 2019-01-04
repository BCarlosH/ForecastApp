package com.example.carlos.forecastapp.data.repository

import androidx.lifecycle.LiveData
import com.example.carlos.forecastapp.data.db.entity.WeatherLocation
import com.example.carlos.forecastapp.data.db.unitlocalized.UnitSpecificCurrentWeatherEntry


interface ForecastRepository {

    suspend fun getCurrentWeather(isMetric: Boolean): LiveData<out UnitSpecificCurrentWeatherEntry>
    suspend fun getWeatherLocation(): LiveData<WeatherLocation>

}