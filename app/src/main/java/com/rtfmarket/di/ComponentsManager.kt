package com.rtfmarket.di

import android.app.Application
import android.content.Context
import com.rtfmarket.di.app.AppComponent
import com.rtfmarket.di.app.AppModule
import com.rtfmarket.di.app.DaggerAppComponent
import com.rtfmarket.di.app.NavigationModule

object ComponentsManager {

    val components: MutableMap<String, Any?> = mutableMapOf()

    lateinit var appComponent: AppComponent


    fun initAppComponent(app: Application) {
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(app))
                .navigationModule(NavigationModule())
                .build()
    }



}