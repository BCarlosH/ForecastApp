package com.example.carlos.forecastapp.data.db.entity

import com.google.gson.annotations.SerializedName


data class Hour(
    @SerializedName("chance_of_rain")
    val chanceOfRain: String,
    @SerializedName("chance_of_snow")
    val chanceOfSnow: String,
    val cloud: Double,
    val condition: Condition,
    @SerializedName("dewpoint_c")
    val dewpointC: Double,
    @SerializedName("dewpoint_f")
    val dewpointF: Double,
    @SerializedName("feelslike_c")
    val feelslikeC: Double,
    @SerializedName("feelslike_f")
    val feelslikeF: Double,
    @SerializedName("heatindex_c")
    val heatindexC: Double,
    @SerializedName("heatindex_f")
    val heatindexF: Double,
    val humidity: Double,
    @SerializedName("is_day")
    val isDay: Double,
    @SerializedName("precip_in")
    val precipIn: Double,
    @SerializedName("precip_mm")
    val precipMm: Double,
    @SerializedName("pressure_in")
    val pressureIn: Double,
    @SerializedName("pressure_mb")
    val pressureMb: Double,
    @SerializedName("temp_c")
    val tempC: Double,
    @SerializedName("temp_f")
    val tempF: Double,
    val time: String,
    @SerializedName("time_epoch")
    val timeEpoch: Double,
    @SerializedName("vis_km")
    val visKm: Double,
    @SerializedName("vis_miles")
    val visMiles: Double,
    @SerializedName("will_it_rain")
    val willItRain: Double,
    @SerializedName("will_it_snow")
    val willItSnow: Double,
    @SerializedName("wind_degree")
    val windDegree: Double,
    @SerializedName("wind_dir")
    val windDir: String,
    @SerializedName("wind_kph")
    val windKph: Double,
    @SerializedName("wind_mph")
    val windMph: Double,
    @SerializedName("windchill_c")
    val windchillC: Double,
    @SerializedName("windchill_f")
    val windchillF: Double
)