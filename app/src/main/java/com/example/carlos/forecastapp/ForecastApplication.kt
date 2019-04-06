package com.example.carlos.forecastapp

import android.app.Application
import android.content.Context
import androidx.preference.PreferenceManager
import com.example.carlos.forecastapp.data.repository.ForecastRepository
import com.example.carlos.forecastapp.data.repository.ForecastRepositoryImpl
import com.example.carlos.forecastapp.di.dbModule
import com.example.carlos.forecastapp.di.networkModule
import com.example.carlos.forecastapp.di.providerModule
import com.example.carlos.forecastapp.di.viewModelFactoryModule
import com.google.android.gms.location.LocationServices
import com.jakewharton.threetenabp.AndroidThreeTen
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton


class ForecastApplication : Application(), KodeinAware {


    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@ForecastApplication))

        bind<ForecastRepository>() with singleton {
            ForecastRepositoryImpl(
                instance(),
                instance(),
                instance(),
                instance(),
                instance()
            )
        }

        bind() from provider { LocationServices.getFusedLocationProviderClient(instance<Context>()) }

        import(viewModelFactoryModule)
        import(dbModule)
        import(networkModule)
        import(providerModule)

    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false)
    }

}