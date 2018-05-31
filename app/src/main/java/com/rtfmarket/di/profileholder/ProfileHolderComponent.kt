package com.rtfmarket.di.profileholder

import com.rtfmarket.di.scope.FragmentScope
import com.rtfmarket.ui.bottomnavigation.profile.ProfileHolderFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [
    ProfileHolderModule::class
])
interface ProfileHolderComponent {

    companion object {
        const val NAME = "com.rtfmarket.di.profile.ProfileHolderComponent"
    }

    @Subcomponent.Builder
    interface Builder {
        fun requestProfileModule(profileHolderModule: ProfileHolderModule): Builder
        fun build(): ProfileHolderComponent
    }


    fun inject(profileHolderFragment: ProfileHolderFragment)
}