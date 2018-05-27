package com.rtfmarket.ui.bottomnavigation.navigation

import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment
import com.rtfmarket.R
import com.rtfmarket.common.constants.Screens
import com.rtfmarket.common.navigation.AppSupportNavigator
import com.rtfmarket.ui.bottomnavigation.BottomNavigationTabFragment
import com.rtfmarket.ui.bottomnavigation.category.CategoryFragment
import kotlinx.android.synthetic.main.fragment_bottom_navigation_tab.view.*

class BottomNavigationTabNavigator(bottomNavigationTabFragment: BottomNavigationTabFragment):
        AppSupportNavigator(
                bottomNavigationTabFragment.activity!!,
                bottomNavigationTabFragment.childFragmentManager,
                R.id.holderBottomNavigationTab
        ) {
    override fun createActivityIntent(context: Context?, screenKey: String?, data: Any?): Intent? = null

    override fun createFragment(screenKey: String?, data: Any?): Fragment? {
        return when(screenKey) {
            Screens.CATEGORY.screenName -> CategoryFragment()
            else -> null
        }
    }


}