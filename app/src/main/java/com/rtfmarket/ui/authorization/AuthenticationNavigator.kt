package com.rtfmarket.ui.authorization

import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment
import com.rtfmarket.R
import com.rtfmarket.common.constants.Screens
import com.rtfmarket.common.navigation.AppSupportNavigator
import com.rtfmarket.ui.bottomnavigation.navigation.BottomNavigationTabRouter
import kotlinx.android.synthetic.main.fragment_authentication.view.*
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.SupportAppNavigator

class AuthenticationNavigator(private val authenticationFragment: AuthenticationFragment,
                              private val router: Router):
        AppSupportNavigator(
                authenticationFragment.activity!!,
                authenticationFragment.childFragmentManager,
                R.id.holderAuthentication) {

    override fun createActivityIntent(context: Context?, screenKey: String?, data: Any?): Intent? = null

    override fun createFragment(screenKey: String?, data: Any?): Fragment? {
        return when(screenKey) {
            Screens.SIGN_IN.screenName -> SignInFragment()
            Screens.SIGN_UP.screenName -> SignUpFragment()
            else -> null
        }
    }

    override fun exit() {
        router.exit()
    }
}