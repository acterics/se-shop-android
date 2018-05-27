package com.rtfmarket.ui

import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment
import com.rtfmarket.R
import com.rtfmarket.common.constants.Screens
import com.rtfmarket.common.navigation.AppSupportNavigator
import com.rtfmarket.ui.bottomnavigation.BottomNavigationHolderFragment
import com.rtfmarket.ui.ordering.OrderingFragment


class MainNavigator(mainActivity: MainActivity):
        AppSupportNavigator(mainActivity, mainActivity.supportFragmentManager, R.id.holderContent) {
    override fun createActivityIntent(context: Context?, screenKey: String?, data: Any?): Intent? = null

    override fun createFragment(screenKey: String?, data: Any?): Fragment? {
        return when(screenKey) {
            Screens.BOTTOM_NAVIGATION.screenName -> BottomNavigationHolderFragment()
            Screens.ORDERING.screenName -> OrderingFragment()
            else -> null
        }
    }


}