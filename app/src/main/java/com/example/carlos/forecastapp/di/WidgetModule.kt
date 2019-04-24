package com.example.carlos.forecastapp.di

import com.example.carlos.forecastapp.ui.widget.ForecastAppWidgetManager
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton


private const val MODULE_NAME = "Widget Module"


val widgetModule = Kodein.Module(MODULE_NAME, false) {

    bind() from singleton { ForecastAppWidgetManager(instance(), instance()) }

}