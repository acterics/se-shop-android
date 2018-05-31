package com.rtfmarket.presentation.profiledata

import com.arellomobile.mvp.MvpView

interface ProfileDataView: MvpView {
    fun showGreeting(username: String)
}