package com.example.carlos.forecastapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.carlos.forecastapp.data.db.entity.FutureWeatherEntry
import com.example.carlos.forecastapp.data.db.unitlocalized.future.detail.ImperialDetailFutureWeatherEntry
import com.example.carlos.forecastapp.data.db.unitlocalized.future.detail.MetricDetailFutureWeatherEntry
import com.example.carlos.forecastapp.data.db.unitlocalized.future.list.ImperialFutureWeatherEntry
import com.example.carlos.forecastapp.data.db.unitlocalized.future.list.MetricFutureWeatherEntry
import org.threeten.bp.LocalDate


@Dao
interface FutureWeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(futureWeatherEntries: List<FutureWeatherEntry>)

    @Query("select * from future_weather where date(date) >= date(:startDate)")
    fun getSimpleWeatherForecastMetric(startDate: LocalDate): LiveData<List<MetricFutureWeatherEntry>>

    @Query("select * from future_weather where date(date) >= date(:startDate)")
    fun getSimpleWeatherForecastImperial(startDate: LocalDate): LiveData<List<ImperialFutureWeatherEntry>>

    @Query("select * from future_weather where date(date) = date(:date)")
    fun getDetailedWeatherByDateMetric(date: LocalDate): LiveData<MetricDetailFutureWeatherEntry>

    @Query("select * from future_weather where date(date) = date(:date)")
    fun getDetailedWeatherByDateImperial(date: LocalDate): LiveData<ImperialDetailFutureWeatherEntry>

    @Query("select count(id) from future_weather where date(date) >= date(:startDate)")
    fun countFutureWeather(startDate: LocalDate): Int

    @Query("delete from future_weather where date(date) < date(:firstDateToKeep)")
    fun deleteOldEntries(firstDateToKeep: LocalDate)


    @Query("select * from future_weather where date(date) = date(:date)")
    fun getWidgetDetailedWeatherByDateMetric(date: LocalDate): MetricDetailFutureWeatherEntry

    @Query("select * from future_weather where date(date) = date(:date)")
    fun getWidgetDetailedWeatherByDateImperial(date: LocalDate): ImperialDetailFutureWeatherEntry

}