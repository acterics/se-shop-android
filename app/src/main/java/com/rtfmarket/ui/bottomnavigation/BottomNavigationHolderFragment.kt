package com.rtfmarket.ui.bottomnavigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem
import com.rtfmarket.R
import com.rtfmarket.common.BackPressListener
import com.rtfmarket.common.BaseFragment
import com.rtfmarket.common.constants.Screens
import com.rtfmarket.common.extension.dsl.subcomponent
import com.rtfmarket.common.extension.getColorCompat
import com.rtfmarket.di.bottomnavigation.BottomNavigationComponent
import com.rtfmarket.di.bottomnavigation.BottomNavigationModule
import com.rtfmarket.di.main.MainComponent
import com.rtfmarket.presentation.bottomnavigation.BottomNavigationHolderPresenter
import com.rtfmarket.presentation.bottomnavigation.BottomNavigationHolderView
import com.rtfmarket.ui.bottomnavigation.navigation.BottomNavigationHolderNavigator
import com.rtfmarket.ui.bottomnavigation.navigation.BottomNavigationRouter
import kotlinx.android.synthetic.main.fragment_bottom_navigation.*
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class BottomNavigationHolderFragment:
        BaseFragment(),
        BottomNavigationHolderNavigator.BottomNavigationCallback,
        BottomNavigationHolderView,
        BackPressListener {

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

    @InjectPresenter lateinit var presenter: BottomNavigationHolderPresenter
    @ProvidePresenter fun providePresenter(): BottomNavigationHolderPresenter {
        return BottomNavigationHolderPresenter(globalRouter, tabHolderRouter)
    }

    private lateinit var onTabSelectListener: AHBottomNavigation.OnTabSelectedListener

    override fun injectComponent() { component?.inject(this) }
    override fun rejectComponent() { component = null }
    override fun setupNavigator() { cicerone.navigatorHolder.setNavigator(navigator) }
    override fun removeNavigator() { cicerone.navigatorHolder.removeNavigator() }

    override fun onCreateInitialized(savedInstanceState: Bundle?) {
        super.onCreateInitialized(savedInstanceState)
        navigator.onCreate()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_bottom_navigation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onTabSelectListener = AHBottomNavigation.OnTabSelectedListener { position, wasSelected ->
            presenter.onBottomNavigationItemClick(position, wasSelected)
        }

        with(vBottomNavigation) {
            isTranslucentNavigationEnabled = true
            accentColor = context.getColorCompat(R.color.colorPrimary)

            addItems(listOf(
                    AHBottomNavigationItem(R.string.catalog, R.drawable.ic_nav_catalog, true),
                    AHBottomNavigationItem(R.string.cart, R.drawable.ic_nav_cart, true),
                    AHBottomNavigationItem(R.string.profile, R.drawable.ic_nav_profile, true)
            ))
            titleState = com.aurelhubert.ahbottomnavigation.AHBottomNavigation.TitleState.ALWAYS_SHOW
            setNotificationBackgroundColor(context.getColorCompat(R.color.colorPrimary))
            setOnTabSelectedListener(onTabSelectListener)
        }
    }

    override fun onFragmentAttached(position: Int) {
        vBottomNavigation.setCurrentItem(position, false)
    }

    override fun onBackCommand() {
        presenter.onBackCommand()
    }


    override fun showExitConfirmMessage() {
        Toast.makeText(context, R.string.exit_confirm_message, Toast.LENGTH_SHORT).show()
    }

    override fun onBackPressed(): Boolean {
        return childFragmentManager.findFragmentById(R.id.holderBottomNavigationContent) ?.let {
            (it as? BackPressListener)?.onBackPressed()
        } ?: presenter.onBackPressed()
    }
}