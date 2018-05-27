package com.rtfmarket.ui

import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.rtfmarket.R
import com.rtfmarket.common.BaseActivity
import com.rtfmarket.common.constants.Screens
import com.rtfmarket.di.ComponentsManager
import com.rtfmarket.di.app.NavigationModule.Companion.NAME_ROUTER_ROOT
import com.rtfmarket.presentation.main.MainPresenter
import com.rtfmarket.presentation.main.MainView
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import javax.inject.Inject
import javax.inject.Named

class MainActivity: BaseActivity(), MainView {

    @Inject lateinit var navigatorHolder: NavigatorHolder
    @Inject lateinit var mainNavigator: MainNavigator
    @Inject @Named(NAME_ROUTER_ROOT) lateinit var router: Router

    @InjectPresenter lateinit var presenter: MainPresenter

    override fun onCreateInitialized(savedInstanceState: Bundle?) {
        super.onCreateInitialized(savedInstanceState)
        setContentView(R.layout.activity_main)
        router.navigateTo(Screens.BOTTOM_NAVIGATION.screenName)
    }

    override fun injectComponent() {
        ComponentsManager.appComponent.inject(this)
    }

    override fun setupNavigator() {
        navigatorHolder.setNavigator(mainNavigator)
    }
}