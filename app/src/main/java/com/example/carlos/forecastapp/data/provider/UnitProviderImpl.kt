package com.example.carlos.forecastapp.data.provider

import android.content.Context
import com.example.carlos.forecastapp.internal.UnitSystem


const val UNIT_SYSTEM = "UNIT_SYSTEM"


class UnitProviderImpl(context: Context) : PreferenceProvider(context), UnitProvider {


    override fun isMetricUnit(): Boolean {
        return getUnitSystem() == UnitSystem.METRIC
    }

    private fun getUnitSystem(): UnitSystem {
        val selectedName = preferences.getString(UNIT_SYSTEM, UnitSystem.METRIC.name)
        return UnitSystem.valueOf(selectedName!!)
    }

}