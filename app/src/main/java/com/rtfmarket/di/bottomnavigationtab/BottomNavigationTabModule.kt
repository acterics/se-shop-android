package com.rtfmarket.di.bottomnavigationtab

import com.rtfmarket.common.navigation.CiceroneHolder
import com.rtfmarket.di.scope.BottomNavigationTabScope
import com.rtfmarket.ui.bottomnavigation.BottomNavigationTabFragment
import com.rtfmarket.ui.bottomnavigation.navigation.BottomNavigationRouter
import com.rtfmarket.ui.bottomnavigation.navigation.BottomNavigationTabNavigator
import com.rtfmarket.ui.bottomnavigation.navigation.BottomNavigationTabRouter
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import javax.inject.Named

@Module
class BottomNavigationTabModule(private val bottomNavigationTabFragment: BottomNavigationTabFragment) {

    @BottomNavigationTabScope
    @Provides
    fun provideCicerone(ciceroneHolder: CiceroneHolder<BottomNavigationTabRouter>): Cicerone<BottomNavigationTabRouter> {
        return ciceroneHolder[bottomNavigationTabFragment.tabName]
    }

    @BottomNavigationTabScope
    @Provides
    fun provideRouter(cicerone: Cicerone<BottomNavigationTabRouter>): BottomNavigationTabRouter {
        return cicerone.router
    }

    @BottomNavigationTabScope
    @Provides
    fun provideNavigator(tabHolderRouter: BottomNavigationRouter): BottomNavigationTabNavigator {
        return BottomNavigationTabNavigator(bottomNavigationTabFragment, tabHolderRouter)
    }

}