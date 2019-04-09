package com.example.carlos.forecastapp.ui.weather.future.detail

import com.example.carlos.forecastapp.data.provider.UnitProvider
import com.example.carlos.forecastapp.data.repository.ForecastRepository
import com.example.carlos.forecastapp.internal.lazyDeferred
import com.example.carlos.forecastapp.ui.base.BaseWeatherViewModel
import org.threeten.bp.LocalDate


class FutureDetailWeatherViewModel(
    private val detailWeatherDate: LocalDate,
    private val forecastRepository: ForecastRepository,
    unitProvider: UnitProvider
) : BaseWeatherViewModel(forecastRepository, unitProvider) {


    val weatherDetail by lazyDeferred {
        forecastRepository.getFutureWeatherByDate(detailWeatherDate, isMetricUnit)
    }

}
