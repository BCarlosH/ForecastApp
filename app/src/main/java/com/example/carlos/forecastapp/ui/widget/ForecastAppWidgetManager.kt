package com.example.carlos.forecastapp.ui.widget

import com.example.carlos.forecastapp.data.db.unitlocalized.future.detail.UnitSpecificDetailFutureWeatherEntry
import com.example.carlos.forecastapp.data.provider.UnitProvider
import com.example.carlos.forecastapp.data.repository.widget.ForecastWidgetRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.threeten.bp.LocalDate


class ForecastAppWidgetManager(
    private val forecastWidgetRepository: ForecastWidgetRepository,
    private val unitProvider: UnitProvider
) {


    suspend fun getWidgetWeather(): UnitSpecificDetailFutureWeatherEntry = coroutineScope {
        val widgetWeather = async {
            forecastWidgetRepository.getWidgetWeather(LocalDate.now(), unitProvider.isMetricUnit())
        }

        widgetWeather.await()
    }

}