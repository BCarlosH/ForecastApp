package com.example.carlos.forecastapp.di

import com.example.carlos.forecastapp.ui.weather.current.CurrentWeatherViewModelFactory
import com.example.carlos.forecastapp.ui.weather.future.detail.FutureDetailWeatherViewModelFactory
import com.example.carlos.forecastapp.ui.weather.future.list.FutureWeatherViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.factory
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.threeten.bp.LocalDate


private const val MODULE_NAME = "ViewModelFactory Module"


val viewModelFactoryModule = Kodein.Module(MODULE_NAME, false) {

    bind() from provider { CurrentWeatherViewModelFactory(instance(), instance()) }
    bind() from provider { FutureWeatherViewModelFactory(instance(), instance()) }
    bind() from factory { detailWeatherDate: LocalDate -> FutureDetailWeatherViewModelFactory(detailWeatherDate, instance(), instance()) }

}
