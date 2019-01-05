package com.example.carlos.forecastapp.di

import com.example.carlos.forecastapp.data.network.*
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton


private const val MODULE_NAME = "Network Module"


val networkModule = Kodein.Module(MODULE_NAME, false) {

    bind() from singleton { ApixuWeatherApiService(instance()) }
    bind<WeatherNetworkDataSource>() with singleton { WeatherNetworkDataSourceImpl(instance()) }
    bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }


}