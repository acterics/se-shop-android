package com.rtfmarket.ui.bottomnavigation.profile.profilenotauthorized

import com.rtfmarket.common.BaseFragment
import com.rtfmarket.di.profileholder.ProfileHolderComponent

class ProfileNotAuthFragment: BaseFragment() {

    override fun injectComponent() {
        getComponent<ProfileHolderComponent>(ProfileHolderComponent.NAME)?.inject(this)
    }
}