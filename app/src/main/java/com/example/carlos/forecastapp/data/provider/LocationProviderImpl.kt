package com.example.carlos.forecastapp.data.provider

import com.example.carlos.forecastapp.data.db.entity.WeatherLocation

class LocationProviderImpl : LocationProvider {


    //TODO: this will be the next step

    override suspend fun getPreferredLocationString(): String {
        return "Madrid"
    }

    override suspend fun hasLocationChanged(lastWeatherLocation: WeatherLocation): Boolean {
        return true
    }

}