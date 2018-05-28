package com.rtfmarket.di.app

import com.rtfmarket.RtfmarketApplication
import com.rtfmarket.di.main.MainComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    NavigationModule::class,
    NetworkModule::class,
    DataModule::class
])
interface AppComponent {

    fun mainComponentBuilder(): MainComponent.Builder

    fun inject(application: RtfmarketApplication)
}