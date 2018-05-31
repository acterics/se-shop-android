package com.rtfmarket.ui.bottomnavigation.profile.profilenotauthorized

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rtfmarket.R
import com.rtfmarket.common.BaseFragment
import com.rtfmarket.common.constants.Screens
import com.rtfmarket.di.profileholder.ProfileHolderComponent
import kotlinx.android.synthetic.main.fragment_profile_not_auth.*
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class ProfileNotAuthFragment: BaseFragment() {

    override fun injectComponent() {
        getComponent<ProfileHolderComponent>(ProfileHolderComponent.NAME)?.inject(this)
    }


    @Inject lateinit var globalRouter: Router

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_profile_not_auth, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btAuthorize.setOnClickListener {
            globalRouter.navigateTo(Screens.AUTHENTICATION.screenName)
        }
    }



}