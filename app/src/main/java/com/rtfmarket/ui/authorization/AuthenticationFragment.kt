package com.rtfmarket.ui.authorization

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rtfmarket.R
import com.rtfmarket.common.BackPressListener
import com.rtfmarket.common.BaseFragment
import com.rtfmarket.common.constants.Screens
import com.rtfmarket.common.extension.dsl.subcomponent
import com.rtfmarket.di.authentication.AuthenticationComponent
import com.rtfmarket.di.authentication.AuthenticationModule
import com.rtfmarket.di.main.MainComponent
import com.rtfmarket.domain.interactor.AuthenticationInteractor
import ru.terrakok.cicerone.Cicerone
import javax.inject.Inject

class AuthenticationFragment: BaseFragment(), BackPressListener {

    private var component by subcomponent<AuthenticationComponent, MainComponent>(
            AuthenticationComponent.NAME, MainComponent.NAME
    ) {
        authenticationComponentBuilder()
                .requestAuthenticationModule(AuthenticationModule(this@AuthenticationFragment))
                .build()
    }


    @Inject lateinit var authenticationInteractor: AuthenticationInteractor
    @Inject lateinit var cicerone: Cicerone<AuthenticationRouter>
    @Inject lateinit var navigator: AuthenticationNavigator
    @Inject lateinit var router: AuthenticationRouter

    override fun injectComponent() { component?.inject(this) }
    override fun rejectComponent() { component = null }
    override fun setupNavigator() { cicerone.navigatorHolder.setNavigator(navigator) }
    override fun removeNavigator() { cicerone.navigatorHolder.removeNavigator() }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_authentication, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) {
            router.replaceScreen(Screens.SIGN_IN.screenName)
        }
    }

    override fun onBackPressed(): Boolean {
        val backPressListenerFragment = childFragmentManager.findFragmentById(R.id.holderAuthentication) as? BackPressListener
        val isBackPressConsumed = backPressListenerFragment?.onBackPressed() ?: false
        if (!isBackPressConsumed) {
            router.exit()
        }
        return true
    }
}