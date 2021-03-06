package com.example.carlos.forecastapp.data.network

import com.example.carlos.forecastapp.data.network.response.CurrentWeatherResponse
import com.example.carlos.forecastapp.data.network.response.FutureWeatherResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


const val API_KEY = "80661e606964451f970115645190301"
const val BASE_URL = "https://api.apixu.com/v1/"

// http://api.apixu.com/v1/current.json?key=80661e606964451f970115645190301&q=Madrid


interface ApixuWeatherApiService {

    @GET("current.json")
    fun getCurrentWeatherAsync(
        @Query("q") location: String
    ): Deferred<CurrentWeatherResponse>

    @GET("forecast.json")
    fun getFutureWeatherAsync(
        @Query("q") location: String,
        @Query("days") days: Int
    ): Deferred<FutureWeatherResponse>


    companion object {
        operator fun invoke(
            connectivityInterceptor: ConnectivityInterceptor
        ): ApixuWeatherApiService {
            val requestInterceptor = Interceptor { chain ->

                val url = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("key", API_KEY)
                    .build()

                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()

                return@Interceptor chain.proceed(request)
            }

            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .addInterceptor(connectivityInterceptor)
                .addInterceptor(logging)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ApixuWeatherApiService::class.java)
        }
    }

}