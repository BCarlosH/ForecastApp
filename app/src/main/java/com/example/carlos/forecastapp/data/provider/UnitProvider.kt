package com.example.carlos.forecastapp.data.provider

import com.example.carlos.forecastapp.internal.UnitSystem


interface UnitProvider {

    fun getUnitSystem(): UnitSystem

}