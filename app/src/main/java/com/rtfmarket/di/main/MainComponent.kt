package com.rtfmarket.di.main

import com.rtfmarket.di.scope.ActivityScope
import com.rtfmarket.ui.MainActivity
import dagger.Subcomponent

@ActivityScope
@Subcomponent(
        modules = [
                MainModule::class
        ]
)
interface MainComponent {

        @Subcomponent.Builder
        interface Builder {
                @Subcomponent.Builder
                interface Builder {
                        fun requestMainModule(mainModule: MainModule): Builder
                        fun build(): MainComponent
                }

        }

        fun inject(mainActivity: MainActivity)
}