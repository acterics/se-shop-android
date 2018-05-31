package com.rtfmarket.ui.bottomnavigation.profile.profiledata

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rtfmarket.R
import com.rtfmarket.common.BaseFragment
import com.rtfmarket.di.profileholder.ProfileHolderComponent
import com.rtfmarket.presentation.profiledata.ProfileDataView

class ProfileDataFragment: BaseFragment(), ProfileDataView {


    override fun injectComponent() {
        getComponent<ProfileHolderComponent>(ProfileHolderComponent.NAME)?.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_profile_data, container, false)
    }

    override fun showGreeting(username: String) {

    }
}