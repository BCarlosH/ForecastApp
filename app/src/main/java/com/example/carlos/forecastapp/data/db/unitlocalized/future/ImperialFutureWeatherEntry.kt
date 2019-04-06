package com.example.carlos.forecastapp.data.db.unitlocalized.future

import androidx.room.ColumnInfo
import org.threeten.bp.LocalDate


data class ImperialFutureWeatherEntry(
    @ColumnInfo(name = "date")
    override val date: LocalDate,
    @ColumnInfo(name = "avg_temperature_f")
    override val avgTemperature: Double,
    @ColumnInfo(name = "condition_text")
    override val conditionText: String,
    @ColumnInfo(name = "condition_icon_url")
    override val conditionIconUrl: String

) : UnitSpecificSimpleFutureWeatherEntry