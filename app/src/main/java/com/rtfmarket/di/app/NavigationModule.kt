package com.rtfmarket.di.app

import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import javax.inject.Named
import javax.inject.Singleton

@Module
class NavigationModule {

    companion object {
        const val NAME_ROUTER_ROOT = "router_root"
    }


    @Singleton
    @Provides
    fun provideCicerone(): Cicerone<Router> = Cicerone.create()

    @Singleton
    @Provides
    @Named(NAME_ROUTER_ROOT)
    fun provideRootRouter(cicerone: Cicerone<Router>): Router = cicerone.router

    @Singleton
    @Provides
    fun provideNavigatorHolder(cicerone: Cicerone<Router>): NavigatorHolder = cicerone.navigatorHolder



}