package com.example.carlos.forecastapp.ui.weather.future.list

import com.example.carlos.forecastapp.R
import com.example.carlos.forecastapp.data.db.unitlocalized.future.list.MetricFutureWeatherEntry
import com.example.carlos.forecastapp.data.db.unitlocalized.future.list.UnitSpecificSimpleFutureWeatherEntry
import com.example.carlos.forecastapp.internal.glide.GlideApp
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_future_weather.*
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.FormatStyle


class FutureWeatherItem(
    val weatherEntry: UnitSpecificSimpleFutureWeatherEntry
) : Item() {


    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.apply {

            updateConditionText()
            updateDate()
            updateTemperature()
            updateConditionImage()

        }
    }

    override fun getLayout(): Int = R.layout.item_future_weather

    private fun ViewHolder.updateConditionText() {
        textView_condition.text = weatherEntry.conditionText
    }

    private fun ViewHolder.updateDate() {
        val dateFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)
        textView_date.text = weatherEntry.date.format(dateFormatter)
    }

    private fun ViewHolder.updateTemperature() {
        val unitAbbreviation = chooseLocalizedUnitAbbreviation(
            itemView.context.getString(R.string.celcius),
            itemView.context.getString(R.string.farenheit)
        )

        textView_temperature.text = itemView.context.getString(
            R.string.blank_templerature,
            weatherEntry.avgTemperature,
            unitAbbreviation
        )
    }

    private fun chooseLocalizedUnitAbbreviation(metric: String, imperial: String): String {
        return if (weatherEntry is MetricFutureWeatherEntry) metric else imperial
    }

    private fun ViewHolder.updateConditionImage() {
        GlideApp.with(this.containerView)
            .load("http:${weatherEntry.conditionIconUrl}")
            .into(imageView_condition_icon)
    }

}