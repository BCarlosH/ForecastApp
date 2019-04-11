package com.example.carlos.forecastapp.internal

import com.example.carlos.forecastapp.data.db.entity.Hour
import com.example.carlos.forecastapp.data.db.unitlocalized.future.list.UnitSpecificSimpleFutureWeatherEntry
import com.example.carlos.forecastapp.ui.weather.future.detail.HourWeatherItem
import com.example.carlos.forecastapp.ui.weather.future.list.FutureWeatherItem


fun List<UnitSpecificSimpleFutureWeatherEntry>.toFutureWeatherItems(): List<FutureWeatherItem> {
    return this.map { FutureWeatherItem(it) }
}

fun List<Hour>.toHourWeatherItems(isMetricUnit: Boolean): List<HourWeatherItem> {
    return this.map { HourWeatherItem(it, isMetricUnit) }
}