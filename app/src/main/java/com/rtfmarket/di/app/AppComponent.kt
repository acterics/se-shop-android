package com.rtfmarket.di.app

import com.rtfmarket.di.app.AppModule
import com.rtfmarket.ui.MainActivity
import com.rtfmarket.RtfmarketApplication
import dagger.Component
import javax.inject.Singleton

/**
* Created by Oleg Lipskiy on 25.01.18.
*/
@Singleton
@Component(modules = [
    AppModule::class
])
interface AppComponent {

    fun inject(application: RtfmarketApplication)
    fun inject(mainActivity: MainActivity)
}