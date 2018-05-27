package com.rtfmarket.ui

import com.arellomobile.mvp.presenter.InjectPresenter
import com.rtfmarket.common.BaseActivity
import com.rtfmarket.di.ComponentsManager
import com.rtfmarket.domain.executor.ExecutionScheduler
import com.rtfmarket.presentation.main.MainPresenter
import com.rtfmarket.presentation.main.MainView
import javax.inject.Inject

class MainActivity: BaseActivity(), MainView {

    @Inject lateinit var executionScheduler: ExecutionScheduler

    @InjectPresenter lateinit var presenter: MainPresenter


    override fun injectComponent() {
        ComponentsManager.appComponent.inject(this)
    }

    override fun setupNavigatorHolder() {}
}