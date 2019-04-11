package com.example.carlos.forecastapp.ui.weather.future.detail

import com.example.carlos.forecastapp.R
import com.example.carlos.forecastapp.data.db.entity.Hour
import com.example.carlos.forecastapp.internal.glide.GlideApp
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_hour_weather_detail.*


class HourWeatherItem(
    private val hourEntry: Hour,
    private val isMetricUnit: Boolean
) : Item() {


    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.apply {

            updateHourText()
            updateTemperature()
            updateConditionImage()

        }
    }

    override fun getLayout(): Int = R.layout.item_hour_weather_detail

    private fun ViewHolder.updateHourText() {
        textView_hour.text = hourEntry.time.substring(hourEntry.time.indexOf(" ") + 1)
    }

    private fun ViewHolder.updateTemperature() {
        val unitAbbreviation = getLocalizedUnitAbbreviation(
            itemView.context.getString(R.string.celcius),
            itemView.context.getString(R.string.farenheit)
        )

        val localizedTemperature = getLocalizedTemperature(
            hourEntry.tempC,
            hourEntry.tempF
        )

        textView_hour_temperature.text = itemView.context.getString(
            R.string.blank_templerature,
            localizedTemperature,
            unitAbbreviation
        )
    }

    private fun getLocalizedUnitAbbreviation(metric: String, imperial: String): String {
        return if (isMetricUnit) metric else imperial
    }

    private fun getLocalizedTemperature(tempC: Double, tempF: Double): Double {
        return if (isMetricUnit) tempC else tempF
    }

    private fun ViewHolder.updateConditionImage() {
        GlideApp.with(this.containerView)
            .load("http:${hourEntry.condition.icon}")
            .into(imageView_hour_condition_icon)
    }

}