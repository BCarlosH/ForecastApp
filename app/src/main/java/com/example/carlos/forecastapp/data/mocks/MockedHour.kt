package com.example.carlos.forecastapp.data.mocks

import com.example.carlos.forecastapp.data.db.entity.Condition
import com.example.carlos.forecastapp.data.db.entity.FutureWeatherEntry
import com.example.carlos.forecastapp.data.db.entity.Hour
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import java.io.FileReader

class MockedHour {

    companion object {

        fun mockHours(list: List<FutureWeatherEntry>): List<FutureWeatherEntry> {

            val newFutureWeatherEntryList = mutableListOf<FutureWeatherEntry>()

            list.forEach {
                newFutureWeatherEntryList.add(it.copy(hour = getMockedHour()))
//                newFutureWeatherEntryList.add(it.copy(hour = restoreHourListFromJsonFile(fileName)))
            }

            return newFutureWeatherEntryList
        }

        private fun getMockedHour(): List<Hour> {
            val mockedHourList = mutableListOf<Hour>()

            mockedHourList.add(
                Hour(
                    "94", "3", 100.0,
                    Condition(1153, "//cdn.apixu.com/weather/64x64/night/266.png", "Light drizzle"),
                    1.0, 33.8, -2.6, 27.3, 1.2, 34.2,
                    98.0, 0.0, 0.02, 0.6, 29.8, 993.0,
                    1.2, 34.2, "2018-03-03 00:00", 1520035200.0, 10.2, 6.0,
                    1.0, 0.0, 86.0, "E", 13.3, 8.3,
                    -2.6, 27.3
                )
            )

            //TODO: improve the mocks a little
            mockedHourList.add(
                Hour(
                    "94", "3", 100.0,
                    Condition(1153, "//cdn.apixu.com/weather/64x64/night/266.png", "Light drizzle"),
                    1.0, 33.8, -2.6, 27.3, 1.2, 34.2,
                    98.0, 0.0, 0.02, 0.6, 29.8, 993.0,
                    1.2, 34.2, "2018-03-03 01:00", 1520035200.0, 10.2, 6.0,
                    1.0, 0.0, 86.0, "E", 13.3, 8.3,
                    -2.6, 27.3
                )
            )

            mockedHourList.add(
                Hour(
                    "94", "3", 100.0,
                    Condition(1153, "//cdn.apixu.com/weather/64x64/night/266.png", "Light drizzle"),
                    1.0, 33.8, -2.6, 27.3, 1.2, 34.2,
                    98.0, 0.0, 0.02, 0.6, 29.8, 993.0,
                    1.2, 34.2, "2018-03-03 02:00", 1520035200.0, 10.2, 6.0,
                    1.0, 0.0, 86.0, "E", 13.3, 8.3,
                    -2.6, 27.3
                )
            )

            mockedHourList.add(
                Hour(
                    "94", "3", 100.0,
                    Condition(1153, "//cdn.apixu.com/weather/64x64/night/266.png", "Light drizzle"),
                    1.0, 33.8, -2.6, 27.3, 1.2, 34.2,
                    98.0, 0.0, 0.02, 0.6, 29.8, 993.0,
                    1.2, 34.2, "2018-03-03 03:00", 1520035200.0, 10.2, 6.0,
                    1.0, 0.0, 86.0, "E", 13.3, 8.3,
                    -2.6, 27.3
                )
            )

            mockedHourList.add(
                Hour(
                    "94", "3", 100.0,
                    Condition(1153, "//cdn.apixu.com/weather/64x64/night/266.png", "Light drizzle"),
                    1.0, 33.8, -2.6, 27.3, 1.2, 34.2,
                    98.0, 0.0, 0.02, 0.6, 29.8, 993.0,
                    1.2, 34.2, "2018-03-03 04:00", 1520035200.0, 10.2, 6.0,
                    1.0, 0.0, 86.0, "E", 13.3, 8.3,
                    -2.6, 27.3
                )
            )

            mockedHourList.add(
                Hour(
                    "94", "3", 100.0,
                    Condition(1153, "//cdn.apixu.com/weather/64x64/night/266.png", "Light drizzle"),
                    1.0, 33.8, -2.6, 27.3, 1.2, 34.2,
                    98.0, 0.0, 0.02, 0.6, 29.8, 993.0,
                    1.2, 34.2, "2018-03-03 05:00", 1520035200.0, 10.2, 6.0,
                    1.0, 0.0, 86.0, "E", 13.3, 8.3,
                    -2.6, 27.3
                )
            )

            mockedHourList.add(
                Hour(
                    "94", "3", 100.0,
                    Condition(1153, "//cdn.apixu.com/weather/64x64/night/266.png", "Light drizzle"),
                    1.0, 33.8, -2.6, 27.3, 1.2, 34.2,
                    98.0, 0.0, 0.02, 0.6, 29.8, 993.0,
                    1.2, 34.2, "2018-03-03 06:00", 1520035200.0, 10.2, 6.0,
                    1.0, 0.0, 86.0, "E", 13.3, 8.3,
                    -2.6, 27.3
                )
            )

            mockedHourList.add(
                Hour(
                    "94", "3", 100.0,
                    Condition(1153, "//cdn.apixu.com/weather/64x64/night/266.png", "Light drizzle"),
                    1.0, 33.8, -2.6, 27.3, 1.2, 34.2,
                    98.0, 0.0, 0.02, 0.6, 29.8, 993.0,
                    1.2, 34.2, "2018-03-03 07:00", 1520035200.0, 10.2, 6.0,
                    1.0, 0.0, 86.0, "E", 13.3, 8.3,
                    -2.6, 27.3
                )
            )

            mockedHourList.add(
                Hour(
                    "94", "3", 100.0,
                    Condition(1153, "//cdn.apixu.com/weather/64x64/night/266.png", "Light drizzle"),
                    1.0, 33.8, -2.6, 27.3, 1.2, 34.2,
                    98.0, 0.0, 0.02, 0.6, 29.8, 993.0,
                    1.2, 34.2, "2018-03-03 08:00", 1520035200.0, 10.2, 6.0,
                    1.0, 0.0, 86.0, "E", 13.3, 8.3,
                    -2.6, 27.3
                )
            )

            mockedHourList.add(
                Hour(
                    "94", "3", 100.0,
                    Condition(1153, "//cdn.apixu.com/weather/64x64/night/266.png", "Light drizzle"),
                    1.0, 33.8, -2.6, 27.3, 1.2, 34.2,
                    98.0, 0.0, 0.02, 0.6, 29.8, 993.0,
                    1.2, 34.2, "2018-03-03 09:00", 1520035200.0, 10.2, 6.0,
                    1.0, 0.0, 86.0, "E", 13.3, 8.3,
                    -2.6, 27.3
                )
            )

            mockedHourList.add(
                Hour(
                    "94", "3", 100.0,
                    Condition(1153, "//cdn.apixu.com/weather/64x64/night/266.png", "Light drizzle"),
                    1.0, 33.8, -2.6, 27.3, 1.2, 34.2,
                    98.0, 0.0, 0.02, 0.6, 29.8, 993.0,
                    1.2, 34.2, "2018-03-03 10:00", 1520035200.0, 10.2, 6.0,
                    1.0, 0.0, 86.0, "E", 13.3, 8.3,
                    -2.6, 27.3
                )
            )

            mockedHourList.add(
                Hour(
                    "94", "3", 100.0,
                    Condition(1153, "//cdn.apixu.com/weather/64x64/night/266.png", "Light drizzle"),
                    1.0, 33.8, -2.6, 27.3, 1.2, 34.2,
                    98.0, 0.0, 0.02, 0.6, 29.8, 993.0,
                    1.2, 34.2, "2018-03-03 11:00", 1520035200.0, 10.2, 6.0,
                    1.0, 0.0, 86.0, "E", 13.3, 8.3,
                    -2.6, 27.3
                )
            )

            mockedHourList.add(
                Hour(
                    "94", "3", 100.0,
                    Condition(1153, "//cdn.apixu.com/weather/64x64/night/266.png", "Light drizzle"),
                    1.0, 33.8, -2.6, 27.3, 1.2, 34.2,
                    98.0, 0.0, 0.02, 0.6, 29.8, 993.0,
                    1.2, 34.2, "2018-03-03 12:00", 1520035200.0, 10.2, 6.0,
                    1.0, 0.0, 86.0, "E", 13.3, 8.3,
                    -2.6, 27.3
                )
            )

            mockedHourList.add(
                Hour(
                    "94", "3", 100.0,
                    Condition(1153, "//cdn.apixu.com/weather/64x64/night/266.png", "Light drizzle"),
                    1.0, 33.8, -2.6, 27.3, 1.2, 34.2,
                    98.0, 0.0, 0.02, 0.6, 29.8, 993.0,
                    1.2, 34.2, "2018-03-03 13:00", 1520035200.0, 10.2, 6.0,
                    1.0, 0.0, 86.0, "E", 13.3, 8.3,
                    -2.6, 27.3
                )
            )

            mockedHourList.add(
                Hour(
                    "94", "3", 100.0,
                    Condition(1153, "//cdn.apixu.com/weather/64x64/night/266.png", "Light drizzle"),
                    1.0, 33.8, -2.6, 27.3, 1.2, 34.2,
                    98.0, 0.0, 0.02, 0.6, 29.8, 993.0,
                    1.2, 34.2, "2018-03-03 14:00", 1520035200.0, 10.2, 6.0,
                    1.0, 0.0, 86.0, "E", 13.3, 8.3,
                    -2.6, 27.3
                )
            )

            mockedHourList.add(
                Hour(
                    "94", "3", 100.0,
                    Condition(1153, "//cdn.apixu.com/weather/64x64/night/266.png", "Light drizzle"),
                    1.0, 33.8, -2.6, 27.3, 1.2, 34.2,
                    98.0, 0.0, 0.02, 0.6, 29.8, 993.0,
                    1.2, 34.2, "2018-03-03 15:00", 1520035200.0, 10.2, 6.0,
                    1.0, 0.0, 86.0, "E", 13.3, 8.3,
                    -2.6, 27.3
                )
            )

            mockedHourList.add(
                Hour(
                    "94", "3", 100.0,
                    Condition(1153, "//cdn.apixu.com/weather/64x64/night/266.png", "Light drizzle"),
                    1.0, 33.8, -2.6, 27.3, 1.2, 34.2,
                    98.0, 0.0, 0.02, 0.6, 29.8, 993.0,
                    1.2, 34.2, "2018-03-03 16:00", 1520035200.0, 10.2, 6.0,
                    1.0, 0.0, 86.0, "E", 13.3, 8.3,
                    -2.6, 27.3
                )
            )

            mockedHourList.add(
                Hour(
                    "94", "3", 100.0,
                    Condition(1153, "//cdn.apixu.com/weather/64x64/night/266.png", "Light drizzle"),
                    1.0, 33.8, -2.6, 27.3, 1.2, 34.2,
                    98.0, 0.0, 0.02, 0.6, 29.8, 993.0,
                    1.2, 34.2, "2018-03-03 17:00", 1520035200.0, 10.2, 6.0,
                    1.0, 0.0, 86.0, "E", 13.3, 8.3,
                    -2.6, 27.3
                )
            )

            mockedHourList.add(
                Hour(
                    "94", "3", 100.0,
                    Condition(1153, "//cdn.apixu.com/weather/64x64/night/266.png", "Light drizzle"),
                    1.0, 33.8, -2.6, 27.3, 1.2, 34.2,
                    98.0, 0.0, 0.02, 0.6, 29.8, 993.0,
                    1.2, 34.2, "2018-03-03 18:00", 1520035200.0, 10.2, 6.0,
                    1.0, 0.0, 86.0, "E", 13.3, 8.3,
                    -2.6, 27.3
                )
            )

            mockedHourList.add(
                Hour(
                    "94", "3", 100.0,
                    Condition(1153, "//cdn.apixu.com/weather/64x64/night/266.png", "Light drizzle"),
                    1.0, 33.8, -2.6, 27.3, 1.2, 34.2,
                    98.0, 0.0, 0.02, 0.6, 29.8, 993.0,
                    1.2, 34.2, "2018-03-03 19:00", 1520035200.0, 10.2, 6.0,
                    1.0, 0.0, 86.0, "E", 13.3, 8.3,
                    -2.6, 27.3
                )
            )

            mockedHourList.add(
                Hour(
                    "94", "3", 100.0,
                    Condition(1153, "//cdn.apixu.com/weather/64x64/night/266.png", "Light drizzle"),
                    1.0, 33.8, -2.6, 27.3, 1.2, 34.2,
                    98.0, 0.0, 0.02, 0.6, 29.8, 993.0,
                    1.2, 34.2, "2018-03-03 20:00", 1520035200.0, 10.2, 6.0,
                    1.0, 0.0, 86.0, "E", 13.3, 8.3,
                    -2.6, 27.3
                )
            )

            mockedHourList.add(
                Hour(
                    "94", "3", 100.0,
                    Condition(1153, "//cdn.apixu.com/weather/64x64/night/266.png", "Light drizzle"),
                    1.0, 33.8, -2.6, 27.3, 1.2, 34.2,
                    98.0, 0.0, 0.02, 0.6, 29.8, 993.0,
                    1.2, 34.2, "2018-03-03 21:00", 1520035200.0, 10.2, 6.0,
                    1.0, 0.0, 86.0, "E", 13.3, 8.3,
                    -2.6, 27.3
                )
            )

            mockedHourList.add(
                Hour(
                    "94", "3", 100.0,
                    Condition(1153, "//cdn.apixu.com/weather/64x64/night/266.png", "Light drizzle"),
                    1.0, 33.8, -2.6, 27.3, 1.2, 34.2,
                    98.0, 0.0, 0.02, 0.6, 29.8, 993.0,
                    1.2, 34.2, "2018-03-03 22:00", 1520035200.0, 10.2, 6.0,
                    1.0, 0.0, 86.0, "E", 13.3, 8.3,
                    -2.6, 27.3
                )
            )

            mockedHourList.add(
                Hour(
                    "94", "3", 100.0,
                    Condition(1153, "//cdn.apixu.com/weather/64x64/night/266.png", "Light drizzle"),
                    1.0, 33.8, -2.6, 27.3, 1.2, 34.2,
                    98.0, 0.0, 0.02, 0.6, 29.8, 993.0,
                    1.2, 34.2, "2018-03-03 23:00", 1520035200.0, 10.2, 6.0,
                    1.0, 0.0, 86.0, "E", 13.3, 8.3,
                    -2.6, 27.3
                )
            )

            return mockedHourList
        }

        private fun restoreHourListFromJsonFile(fileName: String): List<Hour> {
            val reader = JsonReader(FileReader(fileName))
            return Gson().fromJson(reader, object : TypeToken<List<Hour>>() {}.type)
        }
    }

}