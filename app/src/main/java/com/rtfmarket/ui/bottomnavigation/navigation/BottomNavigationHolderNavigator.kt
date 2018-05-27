package com.rtfmarket.ui.bottomnavigation.navigation

import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.widget.Toast
import com.rtfmarket.common.constants.Screens
import com.rtfmarket.ui.bottomnavigation.BottomNavigationTabFragment
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.commands.Back
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.commands.Replace
import ru.terrakok.cicerone.commands.SystemMessage

class BottomNavigationHolderNavigator(private val fragmentActivity: FragmentActivity,
                                      private val fragmentManager: FragmentManager,
                                      @IdRes private val fragmentContainerRes: Int,
                                      private val bottomNavigationCallback: BottomNavigationCallback): Navigator {
    private var catalogFragment: BottomNavigationTabFragment? = null
    private var cartFragment: BottomNavigationTabFragment? = null
    private var profileFragment: BottomNavigationTabFragment? = null

    fun onCreate() {
        catalogFragment = initFragment(Screens.CATEGORY.screenName)
        cartFragment = initFragment(Screens.CART.screenName)
        profileFragment = initFragment(Screens.PROFILE.screenName)
    }

    override fun applyCommands(commands: Array<out Command>?) { commands?.forEach { applyCommand(it) } }


    private fun applyCommand(command: Command) {
        when(command) {
            is Back -> bottomNavigationCallback.onBackCommand()
            is SystemMessage -> Toast.makeText(fragmentActivity, command.message, Toast.LENGTH_LONG).show()
            is Replace -> {
                val fragments = mapOf(
                        Screens.CATEGORY.position to catalogFragment,
                        Screens.CART.position to cartFragment,
                        Screens.PROFILE.position to profileFragment
                )
                when (command.screenKey) {
                    Screens.CATEGORY.screenName -> attachFragment(fragments, Screens.CATEGORY.position)
                    Screens.CART.screenName -> attachFragment(fragments, Screens.CART.position)
                    Screens.PROFILE.screenName -> attachFragment(fragments, Screens.PROFILE.position)
                }


            }
        }
    }

    private fun <T: Fragment>attachFragment(fragments: Map<Int, T?>, position: Int) {
        bottomNavigationCallback.onFragmentAttached(position)
        fragmentManager.beginTransaction()
                .also {transition ->
                    fragments.entries.forEach { entry ->
                        if (entry.key == position) {
                            transition.attach(entry.value)
                        } else {
                            transition.detach(entry.value)
                        }
                    }
                }
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commitNow()
    }

    private fun initFragment(name: String): BottomNavigationTabFragment {
        return fragmentManager.findFragmentByTag(name) as? BottomNavigationTabFragment
                ?: BottomNavigationTabFragment.newInstance(name).also {
                    fragmentManager.beginTransaction()
                            .add(fragmentContainerRes, it, name)
                            .detach(it)
                            .commitNow()
                }
    }

    interface BottomNavigationCallback {
        fun onFragmentAttached(position: Int)
        fun onBackCommand()
    }
}