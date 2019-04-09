package com.example.carlos.forecastapp.data.repository

import androidx.lifecycle.LiveData
import com.example.carlos.forecastapp.data.db.entity.WeatherLocation
import com.example.carlos.forecastapp.data.db.unitlocalized.current.UnitSpecificCurrentWeatherEntry
import com.example.carlos.forecastapp.data.db.unitlocalized.future.detail.UnitSpecificDetailFutureWeatherEntry
import com.example.carlos.forecastapp.data.db.unitlocalized.future.list.UnitSpecificSimpleFutureWeatherEntry
import org.threeten.bp.LocalDate


interface ForecastRepository {

    suspend fun getCurrentWeather(
        isMetric: Boolean
    ): LiveData<out UnitSpecificCurrentWeatherEntry>

    suspend fun getFutureWeatherList(
        startDate: LocalDate,
        isMetric: Boolean
    ): LiveData<out List<UnitSpecificSimpleFutureWeatherEntry>>

    suspend fun getFutureWeatherByDate(
        date: LocalDate,
        metric: Boolean
    ): LiveData<out UnitSpecificDetailFutureWeatherEntry>

    suspend fun getWeatherLocation(): LiveData<WeatherLocation>

}