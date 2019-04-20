package com.example.carlos.forecastapp.ui.weather.current

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.carlos.forecastapp.R
import com.example.carlos.forecastapp.internal.glide.GlideApp
import com.example.carlos.forecastapp.ui.base.ScopedFragment
import kotlinx.android.synthetic.main.current_weather_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance


class CurrentWeatherFragment : ScopedFragment(), KodeinAware {

    override val kodein: Kodein by kodein()
    private val viewModelFactory: CurrentWeatherViewModelFactory by instance()

    private lateinit var viewModel: CurrentWeatherViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.current_weather_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(CurrentWeatherViewModel::class.java)

        initViews()
        bindUI()
    }

    private fun initViews() {
        updateSupportActionBarSubtitle(getString(R.string.today))
        group_loading.visibility = View.VISIBLE
    }

    private fun bindUI() = launch(Dispatchers.Main) {
        val weatherLocation = viewModel.weatherLocation.await()
        val currentWeather = viewModel.weather.await()


        //Create weatherLocation observer
        weatherLocation.observe(this@CurrentWeatherFragment, Observer { location ->
            if (location == null) return@Observer

            updateSupportActionBarTitleLocation(location.name)
        })

        //Create currentWeather observer
        currentWeather.observe(this@CurrentWeatherFragment, Observer {
            if (it == null) return@Observer

            group_loading.visibility = View.GONE
            updateTemperatures(it.temperature, it.feelsLikeTemperature)
            updateCondition(it.conditionText)
            updatePrecipitation(it.precipitationVolume)
            updateWind(it.windDirection, it.windSpeed)
            updateVisibility(it.visibilityDistance)

            GlideApp.with(this@CurrentWeatherFragment)
                .load("http:${it.conditionIconUrl}")
                .into(imageView_condition_icon)
        })

    }

    private fun chooseLocalizedUnitAbbreviation(metric: String, imperial: String): String {
        return if (viewModel.isMetricUnit) metric else imperial
    }

    private fun updateSupportActionBarTitleLocation(location: String) {
        (activity as? AppCompatActivity)?.supportActionBar?.title = location
    }

    private fun updateSupportActionBarSubtitle(text: String) {
        (activity as? AppCompatActivity)?.supportActionBar?.subtitle = text
    }

    private fun updateTemperatures(temperature: Double, feelsLike: Double) {
        val unitAbbreviation =
            chooseLocalizedUnitAbbreviation(getString(R.string.celcius), getString(R.string.farenheit))

        textView_temperature.text = getString(R.string.blank_templerature, temperature, unitAbbreviation)
        textView_feels_like_temperature.text = getString(R.string.feels_like, feelsLike, unitAbbreviation)
    }

    private fun updateCondition(condition: String) {
        textView_condition.text = condition
    }

    private fun updatePrecipitation(precipitationVolume: Double) {
        val unitAbbreviation =
            chooseLocalizedUnitAbbreviation(getString(R.string.millimeters), getString(R.string.inches))

        textView_precipitation.text = getString(R.string.preciptiation, precipitationVolume, unitAbbreviation)
    }

    private fun updateWind(windDirection: String, windSpeed: Double) {
        val unitAbbreviation =
            chooseLocalizedUnitAbbreviation(getString(R.string.kilometers_per_hour), getString(R.string.miles_per_hour))

        textView_wind.text = getString(R.string.wind, windDirection, windSpeed, unitAbbreviation)
    }

    private fun updateVisibility(visibilityDistance: Double) {
        val unitAbbreviation =
            chooseLocalizedUnitAbbreviation(getString(R.string.kilometers), getString(R.string.miles))
        textView_visibility.text = getString(R.string.visibility, visibilityDistance, unitAbbreviation)
    }

}
