package com.rtfmarket.ui.bottomnavigation.navigation

import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment
import com.rtfmarket.R
import com.rtfmarket.common.constants.Screens
import com.rtfmarket.common.navigation.AppSupportNavigator
import com.rtfmarket.domain.model.Category
import com.rtfmarket.domain.model.Product
import com.rtfmarket.ui.bottomnavigation.BottomNavigationTabFragment
import com.rtfmarket.ui.bottomnavigation.cart.CartFragment
import com.rtfmarket.ui.bottomnavigation.catalog.CatalogFragment
import com.rtfmarket.ui.bottomnavigation.category.CategoryFragment
import com.rtfmarket.ui.bottomnavigation.product.ProductFragment
import com.rtfmarket.ui.bottomnavigation.profile.ProfileHolderFragment

class BottomNavigationTabNavigator(private val tabFragment: BottomNavigationTabFragment,
                                   private val tabHolderRouter: BottomNavigationRouter):
        AppSupportNavigator(
                tabFragment.activity!!,
                tabFragment.childFragmentManager,
                R.id.holderBottomNavigationTab
        ) {
    override fun createActivityIntent(context: Context?, screenKey: String?, data: Any?): Intent? = null

    override fun createFragment(screenKey: String?, data: Any?): Fragment? {
        return when(screenKey) {
            Screens.CATEGORY.screenName -> CategoryFragment.createInstance(tabFragment.tabName, data as Category)
            Screens.CATALOG.screenName -> CatalogFragment.createInstance(tabFragment.tabName)
            Screens.CART.screenName -> CartFragment.createInstance(tabFragment.tabName)
            Screens.PROFILE_HOLDER.screenName -> ProfileHolderFragment.createInstance(tabFragment.tabName)
            Screens.PRODUCT.screenName -> ProductFragment.createInstance(tabFragment.tabName, data as Product)
            else -> null
        }
    }

    override fun exit() {
        tabHolderRouter.exit()
    }


}