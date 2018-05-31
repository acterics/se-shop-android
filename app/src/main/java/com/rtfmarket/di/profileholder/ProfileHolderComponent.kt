package com.rtfmarket.di.profileholder

import com.rtfmarket.di.scope.FragmentScope
import com.rtfmarket.di.scope.ProfileHolderScope
import com.rtfmarket.ui.bottomnavigation.profile.ProfileHolderFragment
import com.rtfmarket.ui.bottomnavigation.profile.profiledata.ProfileDataFragment
import com.rtfmarket.ui.bottomnavigation.profile.profilenotauthorized.ProfileNotAuthFragment
import dagger.Subcomponent

@ProfileHolderScope
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
    fun inject(profileDataFragment: ProfileDataFragment)
    fun inject(profileNotAuthFragment: ProfileNotAuthFragment)
}