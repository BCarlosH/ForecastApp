package com.example.carlos.forecastapp.di

import com.example.carlos.forecastapp.data.provider.LocationProvider
import com.example.carlos.forecastapp.data.provider.LocationProviderImpl
import com.example.carlos.forecastapp.data.provider.UnitProvider
import com.example.carlos.forecastapp.data.provider.UnitProviderImpl
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton


private const val MODULE_NAME = "Provider Module"


val providerModule = Kodein.Module(MODULE_NAME, false) {

    bind<UnitProvider>() with singleton { UnitProviderImpl(instance()) }
    bind<LocationProvider>() with singleton { LocationProviderImpl(instance(), instance()) }

}