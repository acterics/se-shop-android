package com.rtfmarket.common.navigation

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import com.rtfmarket.common.TopScrollable
import com.rtfmarket.common.navigation.command.ScrollTop
import ru.terrakok.cicerone.android.SupportAppNavigator
import ru.terrakok.cicerone.commands.Command

abstract class AppSupportNavigator(protected val fragmentActivity: FragmentActivity,
                                     protected val fragmentManager: FragmentManager,
                                     protected val fragmentContainerId: Int):
        SupportAppNavigator(fragmentActivity, fragmentManager, fragmentContainerId) {

    override fun applyCommand(command: Command?) {
        super.applyCommand(command)
        if (command is ScrollTop) {
            onScrollTop()
        }
    }


    fun getCurrentFragment(): Fragment = fragmentManager.findFragmentById(fragmentContainerId)



    private fun onScrollTop() {
        val fragment = getCurrentFragment()
        if (fragment is TopScrollable) {
            fragment.scrollTop()
        }
    }
}