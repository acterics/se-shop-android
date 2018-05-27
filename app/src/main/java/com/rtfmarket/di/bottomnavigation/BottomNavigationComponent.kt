package com.rtfmarket.di.bottomnavigation

import com.rtfmarket.di.bottomnavigationtab.BottomNavigationTabComponent
import com.rtfmarket.di.scope.BottomNavigationScope
import com.rtfmarket.ui.bottomnavigation.BottomNavigationHolderFragment
import dagger.Subcomponent

@BottomNavigationScope
@Subcomponent(modules = [
    BottomNavigationModule::class
])
interface BottomNavigationComponent {

    companion object {
        const val NAME = "com.rtfmarket.di.main.BottomNavigationComponent"
    }

    @Subcomponent.Builder
    interface Builder {
        fun requestBottomNavigationModule(bottomNavigationModule: BottomNavigationModule): Builder
        fun build(): BottomNavigationComponent
    }

    fun bottomNavigationTabComponentBuilder(): BottomNavigationTabComponent.Builder


    fun inject(bottomNavigationHolderFragment: BottomNavigationHolderFragment)
}