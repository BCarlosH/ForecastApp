package com.example.carlos.forecastapp.ui.weather.current

import androidx.lifecycle.ViewModel;
import com.example.carlos.forecastapp.data.repository.ForecastRepository
import com.example.carlos.forecastapp.internal.UnitSystem
import com.example.carlos.forecastapp.internal.lazyDeferred


class CurrentWeatherViewModel(
    private val forecastRepository: ForecastRepository
) : ViewModel() {

    private val unitSystem = UnitSystem.METRIC //get from settings

    private val isMetric: Boolean
        get() = unitSystem == UnitSystem.METRIC

    val weather by lazyDeferred {
        forecastRepository.getCurrentWeather(isMetric)
    }

}
