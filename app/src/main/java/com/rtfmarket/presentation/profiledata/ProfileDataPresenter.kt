package com.rtfmarket.presentation.profiledata

import com.arellomobile.mvp.MvpPresenter
import com.rtfmarket.domain.interactor.ProfileInteractor
import com.rtfmarket.domain.model.Profile
import io.reactivex.rxkotlin.subscribeBy

class ProfileDataPresenter(private val profileInteractor: ProfileInteractor):
        MvpPresenter<ProfileDataView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        profileInteractor.getProfile()
    }

    private fun onProfileLoaded(profile: Profile) {}

}