package com.example.carlos.forecastapp.ui.weather.current

import com.example.carlos.forecastapp.data.provider.UnitProvider
import com.example.carlos.forecastapp.data.repository.ForecastRepository
import com.example.carlos.forecastapp.internal.lazyDeferred
import com.example.carlos.forecastapp.ui.base.BaseWeatherViewModel


class CurrentWeatherViewModel(
    private val forecastRepository: ForecastRepository,
    unitProvider: UnitProvider
) : BaseWeatherViewModel(forecastRepository, unitProvider) {


    val weather by lazyDeferred {
        forecastRepository.getCurrentWeather(isMetricUnit)
    }

}
