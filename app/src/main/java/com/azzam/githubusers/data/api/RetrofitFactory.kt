package com.azzam.githubusers.data.api

import com.azzam.githubusers.BuildConfig
import android.content.Context
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitFactory {

    fun create(context: Context): UsersApi {
        val okHttpClient = OkHttpClient.Builder()
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        okHttpClient.addInterceptor(ConnectivityInterceptor(context))
        okHttpClient.addInterceptor(loggingInterceptor)
        okHttpClient.readTimeout(25, TimeUnit.SECONDS)
        okHttpClient.connectTimeout(25, TimeUnit.SECONDS)

        return Retrofit.Builder()
            .baseUrl(BuildConfig.URL)
            .client(okHttpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UsersApi::class.java)
    }
}
