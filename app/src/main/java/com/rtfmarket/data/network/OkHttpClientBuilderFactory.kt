package com.rtfmarket.data.network

import com.rtfmarket.BuildConfig
import com.rtfmarket.data.preference.PreferenceSource
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Inject

class OkHttpClientBuilderFactory
@Inject constructor(private val preferencesDataStore: PreferenceSource) {

    fun create(): OkHttpClient.Builder {
        val httpClientBuilder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            httpClientBuilder.addInterceptor(loggingInterceptor)
        }
        httpClientBuilder.addInterceptor { chain ->

            val original = chain.request()
            val request = original.newBuilder()
                    .header("Authorization", "Bearer ${preferencesDataStore.getAuthToken()}")
                    .header("Content-Type", "application/json")
                    .method(original.method(), original.body())
                    .build()
            chain.proceed(request)
        }
        return httpClientBuilder
    }
}