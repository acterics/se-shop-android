package com.rtfmarket.di.main

import com.rtfmarket.di.scope.ActivityScope
import com.rtfmarket.ui.MainActivity
import com.rtfmarket.ui.MainNavigator
import dagger.Module
import dagger.Provides

@Module
class MainModule(private val mainActivity: MainActivity) {

    @ActivityScope
    @Provides
    fun provideMainNavigator(): MainNavigator {
        return MainNavigator(mainActivity)
    }
}