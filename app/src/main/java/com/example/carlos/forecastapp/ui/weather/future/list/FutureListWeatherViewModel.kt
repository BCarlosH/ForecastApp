package com.example.carlos.forecastapp.ui.weather.future.list

import com.example.carlos.forecastapp.data.provider.UnitProvider
import com.example.carlos.forecastapp.data.repository.ForecastRepository
import com.example.carlos.forecastapp.internal.lazyDeferred
import com.example.carlos.forecastapp.ui.base.BaseWeatherViewModel
import org.threeten.bp.LocalDate


class FutureListWeatherViewModel(
    private val forecastRepository: ForecastRepository,
    unitProvider: UnitProvider
) : BaseWeatherViewModel(forecastRepository, unitProvider) {


    val futureWeatherList by lazyDeferred {
        forecastRepository.getFutureWeatherList(LocalDate.now(), isMetricUnit)
    }

}
