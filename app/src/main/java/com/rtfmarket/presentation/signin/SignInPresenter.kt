package com.rtfmarket.presentation.signin

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.rtfmarket.domain.interactor.AuthenticationInteractor
import com.rtfmarket.ui.authorization.AuthenticationRouter


@InjectViewState
class SignInPresenter(private val authenticationInteractor: AuthenticationInteractor,
                      private val authenticationRouter: AuthenticationRouter):
        MvpPresenter<SignInView>() {
    fun onSignIn(email: String, password: String) {
        authenticationInteractor.signIn(email, password)

    }


}