package com.example.carlos.forecastapp.data.repository.widget

import com.example.carlos.forecastapp.data.db.unitlocalized.future.detail.UnitSpecificDetailFutureWeatherEntry
import org.threeten.bp.LocalDate


interface ForecastWidgetRepository {

    suspend fun getWidgetWeather(
        date: LocalDate,
        isMetric: Boolean
    ): UnitSpecificDetailFutureWeatherEntry

}