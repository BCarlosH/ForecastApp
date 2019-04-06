package com.example.carlos.forecastapp.data.db.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(tableName = "future_weather", indices = [Index(value = ["date"], unique = true)])
data class FutureWeatherEntry(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @Embedded
    val astro: Astro,
    val date: String,
    @Embedded
    val day: Day
)