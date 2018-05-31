package com.rtfmarket.ui.authorization

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.rtfmarket.R
import com.rtfmarket.common.BaseFragment
import com.rtfmarket.di.authentication.AuthenticationComponent
import com.rtfmarket.domain.interactor.AuthenticationInteractor
import com.rtfmarket.presentation.signin.SignInPresenter
import com.rtfmarket.presentation.signin.SignInView
import kotlinx.android.synthetic.main.fragment_sign_in.*
import javax.inject.Inject

class SignInFragment: BaseFragment(), SignInView {


    @Inject lateinit var authenticationInteractor: AuthenticationInteractor
    @Inject lateinit var authenticationRouter: AuthenticationRouter

    @InjectPresenter lateinit var presenter: SignInPresenter
    @ProvidePresenter fun providePresenter(): SignInPresenter {
        return SignInPresenter(
                authenticationInteractor,
                authenticationRouter
        )
    }


    override fun injectComponent() {
        getComponent<AuthenticationComponent>(AuthenticationComponent.NAME)?.inject(this)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btSignIn.setOnClickListener {
            presenter.onSignIn("${etEmail.text}", "${etPassword.text}")
        }
    }
}