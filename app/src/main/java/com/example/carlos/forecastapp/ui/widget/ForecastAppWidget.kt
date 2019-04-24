package com.example.carlos.forecastapp.ui.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.bumptech.glide.request.target.AppWidgetTarget
import com.example.carlos.forecastapp.R
import com.example.carlos.forecastapp.internal.glide.GlideApp
import com.example.carlos.forecastapp.ui.MainActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.LateInitKodein
import org.kodein.di.generic.instance


class ForecastAppWidget : AppWidgetProvider() {


    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidgetAsync(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    companion object {

        private val kodein = LateInitKodein()

        internal fun updateAppWidgetAsync(
            context: Context,
            appWidgetManager: AppWidgetManager,
            appWidgetId: Int
        ) = GlobalScope.launch(Dispatchers.Main) {

            kodein.baseKodein = (context.applicationContext as KodeinAware).kodein
            val forecastAppWidgetManager: ForecastAppWidgetManager by kodein.instance()

            // load data from db
            val weatherEntry = forecastAppWidgetManager.getWidgetWeather()

            // Construct the RemoteViews object
            val views = RemoteViews(context.packageName, R.layout.forecast_app_widget)

            //Listener to open the app
            views.setOnClickPendingIntent(R.id.widget_layout, getPendingIntent(context, 0))

            //setting temperature data from data base
            val temperature = weatherEntry.avgTemperature.toString() + context.getString(R.string.degree_symbol)
            views.setTextViewText(R.id.appwidget_temperature, temperature)

            //setting widget image
            val widgetTarget = AppWidgetTarget(context, R.id.appwidget_condition_icon, views, appWidgetId)
            val iconUrl = weatherEntry.conditionIconUrl
            setImage(context, widgetTarget, iconUrl)

            // Instruct the widget manager to update the widget
            appWidgetManager.updateAppWidget(appWidgetId, views)

        }

        private fun setImage(context: Context, widgetTarget: AppWidgetTarget, iconUrl: String) {
            GlideApp.with(context)
                .asBitmap()
                .load("http:$iconUrl")
                .into(widgetTarget)
        }

        private fun getPendingIntent(context: Context, sampleExtraValue: Int): PendingIntent {
            val intent = Intent(context, MainActivity::class.java)

            intent.action = "onWidgetClick"
            intent.putExtra("sampleExtraKey", sampleExtraValue)

            return PendingIntent.getActivity(context, sampleExtraValue, intent, 0)
        }

    }

}

