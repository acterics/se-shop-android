package com.rtfmarket.di.main

import com.rtfmarket.di.bottomnavigation.BottomNavigationComponent
import com.rtfmarket.di.scope.ActivityScope
import com.rtfmarket.ui.MainActivity
import com.rtfmarket.ui.bottomnavigation.BottomNavigationHolderFragment
import dagger.Subcomponent

@ActivityScope
@Subcomponent(
        modules = [
                MainModule::class
        ]
)
interface MainComponent {

        companion object {
            const val NAME = "com.rtfmarket.di.main.MainComponent"
        }

        @Subcomponent.Builder
        interface Builder {
                fun requestMainModule(mainModule: MainModule): Builder
                fun build(): MainComponent
        }

        fun bottomNavigationComponentBuilder(): BottomNavigationComponent.Builder

        fun inject(mainActivity: MainActivity)
}