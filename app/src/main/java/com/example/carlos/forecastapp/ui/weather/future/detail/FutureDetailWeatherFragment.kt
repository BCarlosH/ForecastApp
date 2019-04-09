package com.example.carlos.forecastapp.ui.weather.future.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.carlos.forecastapp.R
import com.example.carlos.forecastapp.data.db.LocalDateConverter
import com.example.carlos.forecastapp.internal.DateNotFoundException
import com.example.carlos.forecastapp.internal.glide.GlideApp
import com.example.carlos.forecastapp.ui.base.ScopedFragment
import kotlinx.android.synthetic.main.future_detail_weather_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.factory
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.FormatStyle


class FutureDetailWeatherFragment : ScopedFragment(), KodeinAware {

    override val kodein: Kodein by closestKodein()
    private val viewModelFactoryInstanceFactory: ((LocalDate) -> FutureDetailWeatherViewModelFactory) by factory()

    private lateinit var viewModel: FutureDetailWeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.future_detail_weather_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val safeArgs = arguments?.let {
            FutureDetailWeatherFragmentArgs.fromBundle(it)
        }
        val date = LocalDateConverter.stringToDate(safeArgs?.dateString) ?: throw DateNotFoundException()
        viewModel = ViewModelProviders.of(this, viewModelFactoryInstanceFactory(date))
            .get(FutureDetailWeatherViewModel::class.java)

        bindUI()
    }

    private fun bindUI() = launch(Dispatchers.Main) {
        val futureWeather = viewModel.weatherDetail.await()
        val weatherLocation = viewModel.weatherLocation.await()

        weatherLocation.observe(this@FutureDetailWeatherFragment, Observer { location ->
            if (location == null) return@Observer

            updateSupportActionBarTitleLocation(location.name)
        })

        futureWeather.observe(this@FutureDetailWeatherFragment, Observer { weatherEntry ->
            if (weatherEntry == null) return@Observer

            updateSupportActionBarSubtitle(weatherEntry.date)
            updateTemperatures(
                weatherEntry.avgTemperature,
                weatherEntry.minTemperature, weatherEntry.maxTemperature
            )
            updateCondition(weatherEntry.conditionText)
            updatePrecipitation(weatherEntry.totalPrecipitation)
            updateWindSpeed(weatherEntry.maxWindSpeed)
            updateVisibility(weatherEntry.avgVisibilityDistance)
            updateUv(weatherEntry.uv)

            GlideApp.with(this@FutureDetailWeatherFragment)
                .load("http:" + weatherEntry.conditionIconUrl)
                .into(imageView_condition_icon)
        })
    }

    private fun updateSupportActionBarTitleLocation(location: String) {
        (activity as? AppCompatActivity)?.supportActionBar?.title = location
    }

    private fun updateSupportActionBarSubtitle(date: LocalDate) {
        (activity as? AppCompatActivity)?.supportActionBar?.subtitle =
            date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM))
    }

    private fun updateTemperatures(temperature: Double, min: Double, max: Double) {
        val unitAbbreviation =
            chooseLocalizedUnitAbbreviation(getString(R.string.celcius), getString(R.string.farenheit))

        textView_temperature.text = getString(R.string.blank_templerature, temperature, unitAbbreviation)
        textView_min_max_temperature.text =
            getString(R.string.min_max_templerature, min, unitAbbreviation, max, unitAbbreviation)
    }

    private fun updateCondition(condition: String) {
        textView_condition.text = condition
    }

    private fun updatePrecipitation(precipitationVolume: Double) {
        val unitAbbreviation =
            chooseLocalizedUnitAbbreviation(getString(R.string.millimeters), getString(R.string.inches))

        textView_precipitation.text = getString(R.string.preciptiation, precipitationVolume, unitAbbreviation)
    }

    private fun updateWindSpeed(windSpeed: Double) {
        val unitAbbreviation =
            chooseLocalizedUnitAbbreviation(getString(R.string.kilometers_per_hour), getString(R.string.miles_per_hour))

        textView_wind.text = getString(R.string.wind_max_speed, windSpeed, unitAbbreviation)
    }

    private fun chooseLocalizedUnitAbbreviation(metric: String, imperial: String): String {
        return if (viewModel.isMetricUnit) metric else imperial
    }

    private fun updateVisibility(visibilityDistance: Double) {
        val unitAbbreviation =
            chooseLocalizedUnitAbbreviation(getString(R.string.kilometers), getString(R.string.miles))
        textView_visibility.text = getString(R.string.visibility, visibilityDistance, unitAbbreviation)
    }

    private fun updateUv(uv: Double) {
        textView_uv.text = getString(R.string.uv, uv)
    }

}
