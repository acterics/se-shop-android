package com.rtfmarket.ui.ordering

import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment
import com.rtfmarket.R
import com.rtfmarket.common.navigation.AppSupportNavigator
import ru.terrakok.cicerone.Router

class OrderingNavigator(private val orderingFragment: OrderingFragment,
                        private val globalRouter: Router):
        AppSupportNavigator(
                orderingFragment.activity!!,
                orderingFragment.childFragmentManager,
                R.id.holderOrdering
        ) {
    override fun createActivityIntent(context: Context?, screenKey: String?, data: Any?): Intent? = null

    override fun createFragment(screenKey: String?, data: Any?): Fragment? {
        return when(screenKey) {

            else -> null
        }
    }

    override fun exit() {
        globalRouter.exit()
    }


}