package com.rtfmarket.ui.authorization

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rtfmarket.R
import com.rtfmarket.common.BaseFragment
import com.rtfmarket.common.extension.dsl.subcomponent
import com.rtfmarket.di.authentication.AuthenticationComponent
import com.rtfmarket.di.authentication.AuthenticationModule
import com.rtfmarket.di.main.MainComponent
import com.rtfmarket.domain.interactor.AuthenticationInteractor
import javax.inject.Inject

class AuthenticationFragment: BaseFragment() {

    private var component by subcomponent<AuthenticationComponent, MainComponent>(
            AuthenticationComponent.NAME, MainComponent.NAME
    ) {
        authenticationComponentBuilder()
                .requestAuthenticationModule(AuthenticationModule())
                .build()
    }


    @Inject lateinit var authenticationInteractor: AuthenticationInteractor

    override fun injectComponent() { component?.inject(this) }
    override fun rejectComponent() { component = null }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_authentication, container, false)
    }



}