package com.example.carlos.forecastapp.internal

import com.example.carlos.forecastapp.data.db.unitlocalized.future.UnitSpecificSimpleFutureWeatherEntry
import com.example.carlos.forecastapp.ui.weather.future.list.FutureWeatherItem


fun List<UnitSpecificSimpleFutureWeatherEntry>.toFutureWeatherItems() : List<FutureWeatherItem> {
    return this.map { FutureWeatherItem(it) }
}