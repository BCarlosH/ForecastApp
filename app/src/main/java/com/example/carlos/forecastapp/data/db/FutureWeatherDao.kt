package com.example.carlos.forecastapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.carlos.forecastapp.data.db.entity.FutureWeatherEntry
import com.example.carlos.forecastapp.data.db.unitlocalized.future.ImperialFutureWeatherEntry
import com.example.carlos.forecastapp.data.db.unitlocalized.future.MetricFutureWeatherEntry
import org.threeten.bp.LocalDate


@Dao
interface FutureWeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(futureWeatherEntries: List<FutureWeatherEntry>)

    @Query("select * from future_weather where date(date) >= date(:startDate)")
    fun getSimpleWeatherForecastMetric(startDate: LocalDate): LiveData<List<MetricFutureWeatherEntry>>

    @Query("select * from future_weather where date(date) >= date(:startDate)")
    fun getSimpleWeatherForecastImperial(startDate: LocalDate): LiveData<List<ImperialFutureWeatherEntry>>

    @Query("select count(id) from future_weather where date(date) >= date(:startDate)")
    fun countFutureWeather(startDate: LocalDate): Int

    @Query("delete from future_weather where date(date) < date(:firstDateToKeep)")
    fun deleteOldEntries(firstDateToKeep: LocalDate)

}