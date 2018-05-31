package com.rtfmarket.presentation.profileholder

import com.arellomobile.mvp.MvpPresenter
import com.rtfmarket.common.constants.Screens
import com.rtfmarket.domain.interactor.ProfileInteractor
import com.rtfmarket.ui.bottomnavigation.profile.ProfileHolderRouter
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy

class ProfileHolderPresenter(private val profileInteractor: ProfileInteractor,
                             private val profileHolderRouter: ProfileHolderRouter):
        MvpPresenter<ProfileHolderView>() {

    private var disposable: Disposable? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        disposable = profileInteractor.isAuthorized().subscribeBy(
                onComplete = this::onAuthorized,
                onError = this::onNotAuthorized
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
    }

    private fun onAuthorized() {
        profileHolderRouter.replaceScreen(Screens.PROFILE_DATA.screenName)
    }

    private fun onNotAuthorized(error: Throwable) {
        profileHolderRouter.replaceScreen(Screens.PROFILE_NOT_AUTH.screenName)
    }
}