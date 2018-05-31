package com.rtfmarket.ui.bottomnavigation.profile

import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment
import com.rtfmarket.R
import com.rtfmarket.common.constants.Screens
import com.rtfmarket.common.navigation.AppSupportNavigator
import com.rtfmarket.ui.bottomnavigation.navigation.BottomNavigationTabRouter
import com.rtfmarket.ui.bottomnavigation.profile.profiledata.ProfileDataFragment
import com.rtfmarket.ui.bottomnavigation.profile.profilenotauthorized.ProfileNotAuthFragment

class ProfileHolderNavigator(private val profileHolderFragment: ProfileHolderFragment,
                             private val tabRouter: BottomNavigationTabRouter):
        AppSupportNavigator(
                profileHolderFragment.activity!!,
                profileHolderFragment.childFragmentManager,
                R.id.holderProfile
        ) {

    override fun createActivityIntent(context: Context?, screenKey: String?, data: Any?): Intent? = null
    override fun createFragment(screenKey: String?, data: Any?): Fragment? {
        return when(screenKey) {
            Screens.PROFILE_DATA.screenName -> ProfileDataFragment()
            Screens.PROFILE_NOT_AUTH.screenName -> ProfileNotAuthFragment()
            else -> null
        }
    }

    override fun exit() {
        tabRouter.exit()
    }
}