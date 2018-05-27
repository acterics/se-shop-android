package com.rtfmarket.ui.bottomnavigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rtfmarket.R
import com.rtfmarket.common.BaseFragment
import com.rtfmarket.common.extension.dsl.subcomponent
import com.rtfmarket.di.bottomnavigation.BottomNavigationComponent
import com.rtfmarket.di.bottomnavigation.BottomNavigationModule
import com.rtfmarket.di.main.MainComponent
import com.rtfmarket.ui.bottomnavigation.navigation.BottomNavigationHolderNavigator
import com.rtfmarket.ui.bottomnavigation.navigation.BottomNavigationRouter
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import javax.inject.Inject
import javax.inject.Named

class BottomNavigationHolderFragment:
        BaseFragment(),
        BottomNavigationHolderNavigator.BottomNavigationCallback {

    private var component by subcomponent<BottomNavigationComponent, MainComponent>(
            BottomNavigationComponent.NAME,
            MainComponent.NAME
    ) {
        bottomNavigationComponentBuilder()
                .requestBottomNavigationModule(BottomNavigationModule(this@BottomNavigationHolderFragment))
                .build()
    }


    @Inject lateinit var cicerone: Cicerone<BottomNavigationRouter>
    @Inject lateinit var navigator: BottomNavigationHolderNavigator
    @Inject lateinit var tabHolderRouter: BottomNavigationRouter
    @Inject lateinit var globalRouter: Router


    override fun injectComponent() { component?.inject(this) }
    override fun rejectComponent() { component = null }
    override fun setupNavigator() { cicerone.navigatorHolder.setNavigator(navigator) }
    override fun removeNavigator() { cicerone.navigatorHolder.removeNavigator() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_bottom_navigation, container, false)
    }

    override fun onFragmentAttached(position: Int) {
        //selectBottomNavigationTab
    }

    override fun onBackCommand() {
        //presenter.onBackCommand()
    }
}