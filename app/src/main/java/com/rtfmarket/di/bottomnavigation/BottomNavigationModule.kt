package com.rtfmarket.di.bottomnavigation

import com.rtfmarket.R
import com.rtfmarket.common.navigation.CiceroneHolder
import com.rtfmarket.di.scope.BottomNavigationScope
import com.rtfmarket.ui.bottomnavigation.BottomNavigationHolderFragment
import com.rtfmarket.ui.bottomnavigation.navigation.BottomNavigationCiceroneHolder
import com.rtfmarket.ui.bottomnavigation.navigation.BottomNavigationHolderNavigator
import com.rtfmarket.ui.bottomnavigation.navigation.BottomNavigationRouter
import com.rtfmarket.ui.bottomnavigation.navigation.BottomNavigationTabRouter
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import javax.inject.Named

@Module
class BottomNavigationModule(private val bottomNavigationHolderFragment: BottomNavigationHolderFragment) {

    @BottomNavigationScope
    @Provides
    fun provideCicerone(): Cicerone<BottomNavigationRouter> = Cicerone.create(BottomNavigationRouter())


    @BottomNavigationScope
    @Provides
    fun provideRouter(cicerone: Cicerone<BottomNavigationRouter>): BottomNavigationRouter {
        return cicerone.router
    }

    @BottomNavigationScope
    @Provides
    fun provideTabCiceroneHolder(cicerone: Cicerone<BottomNavigationRouter>): CiceroneHolder<BottomNavigationTabRouter> {
        return BottomNavigationCiceroneHolder(cicerone.router)
    }

    @BottomNavigationScope
    @Provides
    fun provideNavigator(): BottomNavigationHolderNavigator {
        return BottomNavigationHolderNavigator(
                bottomNavigationHolderFragment.activity!!,
                bottomNavigationHolderFragment.childFragmentManager,
                R.id.holderBottomNavigationContent,
                bottomNavigationHolderFragment
        )
    }
}