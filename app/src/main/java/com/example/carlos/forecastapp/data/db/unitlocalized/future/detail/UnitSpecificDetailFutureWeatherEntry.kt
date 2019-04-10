package com.example.carlos.forecastapp.data.db.unitlocalized.future.detail

import com.example.carlos.forecastapp.data.db.entity.Hour
import org.threeten.bp.LocalDate

interface UnitSpecificDetailFutureWeatherEntry {

    val date: LocalDate
    val maxTemperature: Double
    val minTemperature: Double
    val avgTemperature: Double
    val conditionText: String
    val conditionIconUrl: String
    val maxWindSpeed: Double
    val totalPrecipitation: Double
    val avgVisibilityDistance: Double
    val uv: Double
    val hourList: List<Hour>

}