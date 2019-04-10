package com.example.carlos.forecastapp.data.db

import androidx.room.TypeConverter
import com.example.carlos.forecastapp.data.db.entity.Hour
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object HourListConverter {

    @TypeConverter
    @JvmStatic
    fun restoreHourList(listOfString: String): List<Hour> {
        return Gson().fromJson(listOfString, object : TypeToken<List<Hour>>() {}.type)
    }

    @TypeConverter
    @JvmStatic
    fun saveHourList(listOfString: List<Hour>): String {
        return Gson().toJson(listOfString)
    }

}