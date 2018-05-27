package com.rtfmarket.di.bottomnavigationtab

import com.rtfmarket.di.scope.BottomNavigationTabScope
import com.rtfmarket.ui.bottomnavigation.BottomNavigationTabFragment
import dagger.Subcomponent

@BottomNavigationTabScope
@Subcomponent(modules = [
    BottomNavigationTabModule::class
])
interface BottomNavigationTabComponent {

    @Subcomponent.Builder
    interface Builder {
        fun requestBottomNavigationTabModule(bottomNavigationTabModule: BottomNavigationTabModule): Builder
        fun build(): BottomNavigationTabComponent
    }

    fun inject(bottomNavigationTabFragment: BottomNavigationTabFragment)
}