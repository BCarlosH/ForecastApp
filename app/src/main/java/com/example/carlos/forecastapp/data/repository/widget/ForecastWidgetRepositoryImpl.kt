package com.example.carlos.forecastapp.data.repository.widget

import com.example.carlos.forecastapp.data.db.FutureWeatherDao
import com.example.carlos.forecastapp.data.db.unitlocalized.future.detail.UnitSpecificDetailFutureWeatherEntry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.threeten.bp.LocalDate


class ForecastWidgetRepositoryImpl(
    private val futureWeatherDao: FutureWeatherDao
) : ForecastWidgetRepository {


    override suspend fun getWidgetWeather(
        date: LocalDate,
        isMetric: Boolean
    ): UnitSpecificDetailFutureWeatherEntry {
        return withContext(Dispatchers.IO) {

            if (isMetric) {
                return@withContext futureWeatherDao.getWidgetDetailedWeatherByDateMetric(date)
            } else {
                return@withContext futureWeatherDao.getWidgetDetailedWeatherByDateImperial(date)
            }

        }
    }

}