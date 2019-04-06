package com.example.carlos.forecastapp.data.db

import androidx.room.TypeConverter
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter


object LocalDateConverter {

    @TypeConverter
    @JvmStatic
    fun stringToDate(stringDate: String?) = stringDate?.let {
        LocalDate.parse(it, DateTimeFormatter.ISO_LOCAL_DATE)
    }

    @TypeConverter
    @JvmStatic
    fun dateToString(date: LocalDate?) = date?.also {
        it.format(DateTimeFormatter.ISO_LOCAL_DATE)
    }

}