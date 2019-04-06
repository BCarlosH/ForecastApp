package com.example.carlos.forecastapp.data.repository

import androidx.lifecycle.LiveData
import com.example.carlos.forecastapp.data.db.CurrentWeatherDao
import com.example.carlos.forecastapp.data.db.WeatherLocationDao
import com.example.carlos.forecastapp.data.db.entity.WeatherLocation
import com.example.carlos.forecastapp.data.db.unitlocalized.current.UnitSpecificCurrentWeatherEntry
import com.example.carlos.forecastapp.data.network.WeatherNetworkDataSource
import com.example.carlos.forecastapp.data.network.response.CurrentWeatherResponse
import com.example.carlos.forecastapp.data.provider.LocationProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.ZonedDateTime


class ForecastRepositoryImpl(
    private val currentWeatherDao: CurrentWeatherDao,
    private val weatherLocationDao: WeatherLocationDao,
    private val weatherNetworkDataSource: WeatherNetworkDataSource,
    private val locationProvider: LocationProvider
) : ForecastRepository {


    init {
        weatherNetworkDataSource.downloadedCurrentWeather.observeForever { newCurrentWeather ->
            persistFetchedCurrentWeather(newCurrentWeather)
        }
    }

    override suspend fun getCurrentWeather(isMetric: Boolean): LiveData<out UnitSpecificCurrentWeatherEntry> {
        return withContext(Dispatchers.IO) {
            initWeatherData()

            if (isMetric) {
                return@withContext currentWeatherDao.getWeatherMetric()
            } else {
                return@withContext currentWeatherDao.getWeatherImperial()
            }

        }
    }

    override suspend fun getWeatherLocation(): LiveData<WeatherLocation> {
        return withContext(Dispatchers.IO) {
            return@withContext weatherLocationDao.getLocation()
        }
    }

    private fun persistFetchedCurrentWeather(fetchedWeather: CurrentWeatherResponse) {
        GlobalScope.launch(Dispatchers.IO) {
            currentWeatherDao.upsert(fetchedWeather.currentWeatherEntry)
            weatherLocationDao.upsert(fetchedWeather.location)
        }
    }

    private suspend fun initWeatherData() {
        val lastWeatherLocation = weatherLocationDao.getLocation().value

        if (lastWeatherLocation == null || locationProvider.hasLocationChanged(lastWeatherLocation)) {
            fetchCurrentWeather()
            return
        }

        if (isFetchCurrentWeatherNeeded(lastWeatherLocation.zonedDateTime)) {
            fetchCurrentWeather()
        }
    }

    private suspend fun fetchCurrentWeather() {
        weatherNetworkDataSource.fetchCurrentWeather(locationProvider.getPreferredLocationString())
    }

    private fun isFetchCurrentWeatherNeeded(lastFetchTime: ZonedDateTime): Boolean {
        val thirtyMinutesAgo = ZonedDateTime.now().minusMinutes(30)
        return lastFetchTime.isBefore(thirtyMinutesAgo)
    }

}