package com.example.carlos.forecastapp.di

import com.example.carlos.forecastapp.data.db.ForecastDatabase
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton


private const val MODULE_NAME = "DB Module"


val dbModule = Kodein.Module(MODULE_NAME, false) {

    bind() from singleton { ForecastDatabase(instance()) }
    bind() from singleton { instance<ForecastDatabase>().currentWeatherDao() }
    bind() from singleton { instance<ForecastDatabase>().weatherLocationDao() }

}