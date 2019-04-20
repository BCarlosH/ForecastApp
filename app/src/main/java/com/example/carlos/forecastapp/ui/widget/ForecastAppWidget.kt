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


class ForecastAppWidget : AppWidgetProvider() {


    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }


    companion object {

        internal fun updateAppWidget(
            context: Context,
            appWidgetManager: AppWidgetManager,
            appWidgetId: Int
        ) {
            val widgetText = context.getString(R.string.appwidget_text)


            // Construct the RemoteViews object
            val views = RemoteViews(context.packageName, R.layout.forecast_app_widget)

            //setting temperature data from data base
            val temperature = "" //TODO: get data from DB
            views.setTextViewText(R.id.appwidget_temperature, temperature)

            //setting widget image
            val widgetTarget = AppWidgetTarget(context, R.id.appwidget_condition_icon, views, appWidgetId)
            val iconUrl = "" //TODO: get data from DB
            GlideApp.with(context)
                .asBitmap()
                .load("http:$iconUrl")
                .into(widgetTarget)

            //Listener to open the app
            views.setOnClickPendingIntent(R.id.widget_layout, getPendingIntent(context, 0))

            // Instruct the widget manager to update the widget
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }

        private fun getPendingIntent(context: Context, sampleExtraValue: Int): PendingIntent {
            val intent = Intent(context, MainActivity::class.java)

            intent.action = "onWidgetClick"
            intent.putExtra("sampleExtraKey", sampleExtraValue)

            return PendingIntent.getActivity(context, sampleExtraValue, intent, 0)
        }

    }

}

