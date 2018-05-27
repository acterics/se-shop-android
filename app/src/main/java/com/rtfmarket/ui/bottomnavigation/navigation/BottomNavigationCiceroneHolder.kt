package com.rtfmarket.ui.bottomnavigation.navigation

import com.rtfmarket.common.navigation.CiceroneHolder

class BottomNavigationCiceroneHolder(private val parentRouter: BottomNavigationRouter):
        CiceroneHolder<BottomNavigationTabRouter>() {
    override fun createNewRouter(key: String): BottomNavigationTabRouter {
        return BottomNavigationTabRouter(parentRouter)
    }

}