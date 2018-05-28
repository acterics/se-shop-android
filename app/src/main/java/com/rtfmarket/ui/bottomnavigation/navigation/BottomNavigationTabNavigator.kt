package com.rtfmarket.ui.bottomnavigation.navigation

import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment
import com.rtfmarket.R
import com.rtfmarket.common.constants.Screens
import com.rtfmarket.common.navigation.AppSupportNavigator
import com.rtfmarket.ui.bottomnavigation.BottomNavigationTabFragment
import com.rtfmarket.ui.bottomnavigation.cart.CartFragment
import com.rtfmarket.ui.bottomnavigation.catalog.CatalogFragment
import com.rtfmarket.ui.bottomnavigation.category.CategoryFragment
import com.rtfmarket.ui.bottomnavigation.profile.ProfileFragment
import kotlinx.android.synthetic.main.fragment_bottom_navigation_tab.view.*

class BottomNavigationTabNavigator(private val bottomNavigationTabFragment: BottomNavigationTabFragment):
        AppSupportNavigator(
                bottomNavigationTabFragment.activity!!,
                bottomNavigationTabFragment.childFragmentManager,
                R.id.holderBottomNavigationTab
        ) {
    override fun createActivityIntent(context: Context?, screenKey: String?, data: Any?): Intent? = null

    override fun createFragment(screenKey: String?, data: Any?): Fragment? {
        return when(screenKey) {
            Screens.CATEGORY.screenName -> CategoryFragment()
            Screens.CATALOG.screenName -> CatalogFragment.createInstance(bottomNavigationTabFragment.tabName)
            Screens.CART.screenName -> CartFragment()
            Screens.PROFILE.screenName -> ProfileFragment()
            else -> null
        }
    }


}