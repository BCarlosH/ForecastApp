package com.example.carlos.forecastapp.ui.widget

import com.example.carlos.forecastapp.data.provider.UnitProvider
import com.example.carlos.forecastapp.data.repository.widget.ForecastWidgetRepository
import com.example.carlos.forecastapp.internal.UnitSystem
import com.example.carlos.forecastapp.internal.lazyDeferred
import org.threeten.bp.LocalDate


class ForecastAppWidgetManager(
    private val forecastWidgetRepository: ForecastWidgetRepository,
    unitProvider: UnitProvider
) {


    private val unitSystem = unitProvider.getUnitSystem()

    private val isMetricUnit: Boolean
        get() = unitSystem == UnitSystem.METRIC

    val weather by lazyDeferred {
        forecastWidgetRepository.getWidgetWeather(LocalDate.now(), isMetricUnit)
    }

}