package com.rtfmarket.ui.bottomnavigation.profile.profiledata

import com.rtfmarket.common.BaseFragment
import com.rtfmarket.di.profileholder.ProfileHolderComponent

class ProfileDataFragment: BaseFragment() {
    override fun injectComponent() {
        getComponent<ProfileHolderComponent>(ProfileHolderComponent.NAME)?.inject(this)
    }
}