package com.rtfmarket.ui.authorization

import com.rtfmarket.common.BaseFragment
import com.rtfmarket.di.authentication.AuthenticationComponent

class SignUpFragment: BaseFragment() {

    override fun injectComponent() {
        getComponent<AuthenticationComponent>(AuthenticationComponent.NAME)?.inject(this)
    }
}