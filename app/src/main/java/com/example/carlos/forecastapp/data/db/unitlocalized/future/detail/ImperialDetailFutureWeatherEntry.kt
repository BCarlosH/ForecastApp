package com.example.carlos.forecastapp.data.db.unitlocalized.future.detail

import androidx.room.ColumnInfo
import com.example.carlos.forecastapp.data.db.entity.Hour
import org.threeten.bp.LocalDate


data class ImperialDetailFutureWeatherEntry(
    @ColumnInfo(name = "date")
    override val date: LocalDate,
    @ColumnInfo(name = "maxtempF")
    override val maxTemperature: Double,
    @ColumnInfo(name = "mintempF")
    override val minTemperature: Double,
    @ColumnInfo(name = "avgtempF")
    override val avgTemperature: Double,
    @ColumnInfo(name = "condition_text")
    override val conditionText: String,
    @ColumnInfo(name = "condition_icon")
    override val conditionIconUrl: String,
    @ColumnInfo(name = "maxwindMph")
    override val maxWindSpeed: Double,
    @ColumnInfo(name = "totalprecipIn")
    override val totalPrecipitation: Double,
    @ColumnInfo(name = "avgvisMiles")
    override val avgVisibilityDistance: Double,
    @ColumnInfo(name = "uv")
    override val uv: Double,
    @ColumnInfo(name = "hour")
    override val hourList: List<Hour>
) : UnitSpecificDetailFutureWeatherEntry