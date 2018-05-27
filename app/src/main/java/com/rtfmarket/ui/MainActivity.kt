package com.rtfmarket.ui

import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.rtfmarket.R
import com.rtfmarket.common.BaseActivity
import com.rtfmarket.common.constants.Screens
import com.rtfmarket.common.extension.dsl.subcomponent
import com.rtfmarket.di.ComponentsManager
import com.rtfmarket.di.app.NavigationModule
import com.rtfmarket.di.main.MainComponent
import com.rtfmarket.di.main.MainModule
import com.rtfmarket.presentation.main.MainPresenter
import com.rtfmarket.presentation.main.MainView
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import javax.inject.Inject
import javax.inject.Named

class MainActivity: BaseActivity(), MainView {

    private var component by subcomponent(MainComponent.NAME) {
        mainComponentBuilder()
                .requestMainModule(MainModule(this@MainActivity))
                .build()
    }

    @Inject lateinit var cicerone: Cicerone<Router>
    @Inject lateinit var mainNavigator: MainNavigator
    @Inject lateinit var router: Router

    @InjectPresenter lateinit var presenter: MainPresenter

    override fun onCreateInitialized(savedInstanceState: Bundle?) {
        super.onCreateInitialized(savedInstanceState)
        setContentView(R.layout.activity_main)
        router.navigateTo(Screens.BOTTOM_NAVIGATION.screenName)
    }

    override fun injectComponent() { component?.inject(this) }
    override fun rejectComponent() { component = null }

    override fun setupNavigator() { cicerone.navigatorHolder.setNavigator(mainNavigator) }
}