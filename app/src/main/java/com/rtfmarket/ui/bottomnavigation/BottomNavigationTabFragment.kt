package com.rtfmarket.ui.bottomnavigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rtfmarket.R
import com.rtfmarket.common.BackPressListener
import com.rtfmarket.common.BaseFragment
import com.rtfmarket.common.extension.dsl.lazySubcomponent
import com.rtfmarket.di.bottomnavigation.BottomNavigationComponent
import com.rtfmarket.di.bottomnavigationtab.BottomNavigationTabComponent
import com.rtfmarket.di.bottomnavigationtab.BottomNavigationTabModule
import com.rtfmarket.ui.bottomnavigation.navigation.BottomNavigationRouter
import com.rtfmarket.ui.bottomnavigation.navigation.BottomNavigationTabNavigator
import com.rtfmarket.ui.bottomnavigation.navigation.BottomNavigationTabRouter
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import javax.inject.Inject
import javax.inject.Named

class BottomNavigationTabFragment: BaseFragment(), BackPressListener {

    companion object {
        const val EXTRA_TAB_NAME = "EXTRA_TAB_NAME"
        fun newInstance(tabName: String): BottomNavigationTabFragment {
            return BottomNavigationTabFragment().also {
                it.arguments = Bundle().also {
                    it.putString(EXTRA_TAB_NAME, tabName)
                }
            }
        }
    }

    private var component by lazySubcomponent<BottomNavigationTabComponent, BottomNavigationComponent>(
            { tabName }, BottomNavigationComponent.NAME
    ) {
        bottomNavigationTabComponentBuilder()
                .requestBottomNavigationTabModule(BottomNavigationTabModule(this@BottomNavigationTabFragment))
                .build()
    }

    internal val tabName: String by lazy { arguments!!.getString(EXTRA_TAB_NAME) }


    @Inject lateinit var cicerone: Cicerone<BottomNavigationTabRouter>
    @Inject lateinit var navigator: BottomNavigationTabNavigator
    @Inject lateinit var tabRouter: BottomNavigationTabRouter
    @Inject lateinit var tabHolderRouter: BottomNavigationRouter
    @Inject lateinit var globalRouter: Router

    override fun setupNavigator() { cicerone.navigatorHolder.setNavigator(navigator) }
    override fun removeNavigator() { cicerone.navigatorHolder.removeNavigator() }
    override fun injectComponent() { component?.inject(this) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_bottom_navigation_tab, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (childFragmentManager.findFragmentById(R.id.holderBottomNavigationTab) == null) {
            tabRouter.replaceScreen(tabName)
        }
    }

    override fun onBackPressed(): Boolean {
        val backPressListenerFragment = childFragmentManager.findFragmentById(R.id.holderBottomNavigationTab) as? BackPressListener
        val isBackPressConsumed = backPressListenerFragment?.onBackPressed() ?: false
        if (!isBackPressConsumed) {
            tabRouter.exit()
        }
        return true
    }
}