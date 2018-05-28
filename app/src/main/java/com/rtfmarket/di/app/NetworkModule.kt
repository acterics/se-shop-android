package com.rtfmarket.di.app

import com.google.gson.Gson
import com.rtfmarket.data.network.OkHttpClientBuilderFactory
import com.rtfmarket.data.network.RetrofitBuilderFactory
import com.rtfmarket.data.network.ApiService
import com.rtfmarket.data.network.GsonBuilderFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(okHttpClientBuilderFactory: OkHttpClientBuilderFactory): OkHttpClient {
        return okHttpClientBuilderFactory.create().build()
    }


    @Singleton
    @Provides
    fun provideRetrofit(retrofitBuilderFactory: RetrofitBuilderFactory): Retrofit {
        return retrofitBuilderFactory.create().build()
    }

    @Singleton
    @Provides
    fun provideGson(gsonBuilderFactory: GsonBuilderFactory): Gson {
        return gsonBuilderFactory.create().create()
    }



    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}