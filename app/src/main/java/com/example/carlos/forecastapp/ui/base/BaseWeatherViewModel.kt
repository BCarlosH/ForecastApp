package com.example.carlos.forecastapp.ui.base

import androidx.lifecycle.ViewModel
import com.example.carlos.forecastapp.data.provider.UnitProvider
import com.example.carlos.forecastapp.data.repository.ForecastRepository
import com.example.carlos.forecastapp.internal.UnitSystem
import com.example.carlos.forecastapp.internal.lazyDeferred


abstract class BaseWeatherViewModel(
    private val forecastRepository: ForecastRepository,
    unitProvider: UnitProvider
) : ViewModel() {


    private val unitSystem = unitProvider.getUnitSystem()

    val isMetricUnit: Boolean
        get() = unitSystem == UnitSystem.METRIC

    val weatherLocation by lazyDeferred {
        forecastRepository.getWeatherLocation()
    }

}