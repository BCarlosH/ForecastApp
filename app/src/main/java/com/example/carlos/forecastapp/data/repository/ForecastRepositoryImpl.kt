package com.example.carlos.forecastapp.data.repository

import androidx.lifecycle.LiveData
import com.example.carlos.forecastapp.data.db.CurrentWeatherDao
import com.example.carlos.forecastapp.data.db.FutureWeatherDao
import com.example.carlos.forecastapp.data.db.WeatherLocationDao
import com.example.carlos.forecastapp.data.db.entity.WeatherLocation
import com.example.carlos.forecastapp.data.db.unitlocalized.current.UnitSpecificCurrentWeatherEntry
import com.example.carlos.forecastapp.data.db.unitlocalized.future.detail.UnitSpecificDetailFutureWeatherEntry
import com.example.carlos.forecastapp.data.db.unitlocalized.future.list.UnitSpecificSimpleFutureWeatherEntry
import com.example.carlos.forecastapp.data.mocks.MockedHour
import com.example.carlos.forecastapp.data.network.FORECAST_DAYS_COUNT
import com.example.carlos.forecastapp.data.network.WeatherNetworkDataSource
import com.example.carlos.forecastapp.data.network.response.CurrentWeatherResponse
import com.example.carlos.forecastapp.data.network.response.FutureWeatherResponse
import com.example.carlos.forecastapp.data.provider.LocationProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.LocalDate
import org.threeten.bp.ZonedDateTime


class ForecastRepositoryImpl(
    private val currentWeatherDao: CurrentWeatherDao,
    private val futureWeatherDao: FutureWeatherDao,
    private val weatherLocationDao: WeatherLocationDao,
    private val weatherNetworkDataSource: WeatherNetworkDataSource,
    private val locationProvider: LocationProvider
) : ForecastRepository {

    /**
     * Always true because for now, I wont pay for premium API services
     */
    var mocksNeed = true


    init {
        weatherNetworkDataSource.apply {

            downloadedCurrentWeather.observeForever { newCurrentWeather ->
                persistFetchedCurrentWeather(newCurrentWeather)
            }

            downloadedFutureWeather.observeForever { newFutureWeather ->
                persistFetchedFutureWeather(newFutureWeather)
            }

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

    override suspend fun getFutureWeatherList(
        startDate: LocalDate,
        isMetric: Boolean
    ): LiveData<out List<UnitSpecificSimpleFutureWeatherEntry>> {
        return withContext(Dispatchers.IO) {

            if (isMetric) {
                return@withContext futureWeatherDao.getSimpleWeatherForecastMetric(startDate)
            } else {
                return@withContext futureWeatherDao.getSimpleWeatherForecastImperial(startDate)
            }

        }
    }

    override suspend fun getFutureWeatherByDate(
        date: LocalDate,
        metric: Boolean
    ): LiveData<out UnitSpecificDetailFutureWeatherEntry> {
        return withContext(Dispatchers.IO) {
            initWeatherData()

            if (metric) {
                return@withContext futureWeatherDao.getDetailedWeatherByDateMetric(date)
            } else {
                return@withContext futureWeatherDao.getDetailedWeatherByDateImperial(date)
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

    private fun persistFetchedFutureWeather(fetchedWeather: FutureWeatherResponse) {
        GlobalScope.launch(Dispatchers.IO) {
            deleteOldForecastData()

            val futureWeatherList =
                if (mocksNeed) {
                    MockedHour.mockHours(fetchedWeather.futureWeatherEntries.entries)
                } else {
                    fetchedWeather.futureWeatherEntries.entries
                }

            futureWeatherDao.insert(futureWeatherList)
            weatherLocationDao.upsert(fetchedWeather.location)
        }
    }

    private fun deleteOldForecastData() {
        val today = LocalDate.now()
        futureWeatherDao.deleteOldEntries(today)
    }

    private suspend fun initWeatherData() {
        val lastWeatherLocation = weatherLocationDao.getLocationNonLive()

        if (lastWeatherLocation == null || locationProvider.hasLocationChanged(lastWeatherLocation)) {
            fetchCurrentWeather()
            fetchFutureWeather()
            return
        }

        if (isFetchCurrentWeatherNeeded(lastWeatherLocation.zonedDateTime)) {
            fetchCurrentWeather()
        }

        if (isFetchFutureWeatherNeeded()) {
            fetchFutureWeather()
        }
    }

    private suspend fun fetchCurrentWeather() {
        weatherNetworkDataSource.fetchCurrentWeather(locationProvider.getPreferredLocationString())
    }

    private suspend fun fetchFutureWeather() {
        weatherNetworkDataSource.fetchFutureWeather(locationProvider.getPreferredLocationString())
    }

    private fun isFetchCurrentWeatherNeeded(lastFetchTime: ZonedDateTime): Boolean {
        val thirtyMinutesAgo = ZonedDateTime.now().minusMinutes(30)
        return lastFetchTime.isBefore(thirtyMinutesAgo)
    }

    private fun isFetchFutureWeatherNeeded(): Boolean {
        val today = LocalDate.now()
        val futureWeatherCount = futureWeatherDao.countFutureWeather(today)
        return futureWeatherCount < FORECAST_DAYS_COUNT
    }

}