package com.rtfmarket.ui.bottomnavigation.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rtfmarket.R
import com.rtfmarket.common.BaseFragment
import com.rtfmarket.common.extension.dsl.lazySubcomponent
import com.rtfmarket.di.bottomnavigationtab.BottomNavigationTabComponent
import com.rtfmarket.di.profileholder.ProfileHolderComponent
import com.rtfmarket.di.profileholder.ProfileHolderModule
import com.rtfmarket.ui.bottomnavigation.BottomNavigationTabFragment.Companion.EXTRA_TAB_NAME

class ProfileHolderFragment: BaseFragment() {

    companion object {
        fun createInstance(parentComponentName: String): BaseFragment {
            return ProfileHolderFragment().also {
                it.arguments = Bundle().also { args ->
                    args.putString(EXTRA_TAB_NAME, parentComponentName)
                }
            }
        }
    }

    private val tabName by lazy { arguments!!.getString(EXTRA_TAB_NAME) }
    private var component by lazySubcomponent<ProfileHolderComponent, BottomNavigationTabComponent>(
            ProfileHolderComponent.NAME,
            { tabName }
    ) {
        profileComponentBuilder()
                .requestProfileModule(ProfileHolderModule())
                .build()
    }

    override fun injectComponent() { component?.inject(this) }
    override fun rejectComponent() { component = null }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_profile_holder, container, false)
    }

}