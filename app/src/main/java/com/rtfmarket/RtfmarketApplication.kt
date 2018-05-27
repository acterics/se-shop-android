package com.rtfmarket

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import com.crashlytics.android.Crashlytics
import com.rtfmarket.di.ComponentsManager
import io.fabric.sdk.android.Fabric



class RtfmarketApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        ComponentsManager.initAppComponent(this)
        Fabric.with(this, Crashlytics())
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}