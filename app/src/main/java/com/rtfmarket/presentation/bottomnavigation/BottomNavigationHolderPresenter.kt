package com.rtfmarket.presentation.bottomnavigation

import android.os.Handler
import com.arellomobile.mvp.MvpPresenter
import com.rtfmarket.common.constants.Screens
import com.rtfmarket.common.constants.Screens.*
import com.rtfmarket.common.navigation.CiceroneHolder
import com.rtfmarket.ui.bottomnavigation.navigation.BottomNavigationCiceroneHolder
import com.rtfmarket.ui.bottomnavigation.navigation.BottomNavigationRouter
import com.rtfmarket.ui.bottomnavigation.navigation.BottomNavigationTabRouter
import io.reactivex.rxkotlin.subscribeBy
import ru.terrakok.cicerone.Router

class BottomNavigationHolderPresenter(private val globalRouter: Router,
                                      private val tabHolderRouter: BottomNavigationRouter):
        MvpPresenter<BottomNavigationHolderView>() {

    companion object {
        const val EXIT_CONFIRM_DELAY = 3000L
    }

    private val exitHandler : Handler by lazy { Handler() }
    private var isExitConfirm = false


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        tabHolderRouter.replaceScreen(CATALOG.screenName)
    }


    override fun onDestroy() {
        super.onDestroy()
        exitHandler.removeCallbacksAndMessages(null)
    }

    fun onBottomNavigationItemClick(position: Int, wasSelected: Boolean): Boolean {
        return when(position) {
            CATALOG.position -> tabHolderRouter.replaceScreen(CATALOG.screenName)
            CART.position -> tabHolderRouter.replaceScreen(CART.screenName)
            PROFILE.position -> tabHolderRouter.replaceScreen(PROFILE.screenName)
            else -> null
        }?.let { true } ?: false
    }

    fun onBackPressed() {
        tabHolderRouter.exit()
    }


    fun onBackCommand() {
        if (isExitConfirm) {
            globalRouter.exit()
        } else {
            viewState.showExitConfirmMessage()
            isExitConfirm = true
            exitHandler.postDelayed({
                isExitConfirm = false
            }, EXIT_CONFIRM_DELAY)
        }
    }
}