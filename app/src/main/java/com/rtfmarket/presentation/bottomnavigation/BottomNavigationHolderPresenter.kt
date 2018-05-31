package com.rtfmarket.presentation.bottomnavigation

import android.os.Handler
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.rtfmarket.common.constants.Screens.*
import com.rtfmarket.ui.bottomnavigation.navigation.BottomNavigationRouter
import ru.terrakok.cicerone.Router

@InjectViewState
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
            PROFILE_HOLDER.position -> tabHolderRouter.replaceScreen(PROFILE_HOLDER.screenName)
            else -> null
        }?.let { true } ?: false
    }

    fun onBackPressed(): Boolean {
        return tabHolderRouter.exit().let { true }
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