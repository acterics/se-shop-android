package com.rtfmarket.data.network

import com.google.gson.Gson
import com.rtfmarket.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class RetrofitBuilderFactory
@Inject constructor(private val okHttpClient: OkHttpClient,
                    private val gson: Gson) {
    fun create(): Retrofit.Builder {
        val retrofitBuilder = Retrofit.Builder()
        return retrofitBuilder
                .baseUrl(BuildConfig.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
    }
}