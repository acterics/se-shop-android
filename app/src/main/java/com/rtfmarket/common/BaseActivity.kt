package com.rtfmarket.common


import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity

/**
 * Created by Oleg Lipskiy on 25.01.18
 */
abstract class BaseActivity : MvpAppCompatActivity() {

    private var isNavigatorWasSetup = false

    protected abstract fun injectComponent()
    protected open fun rejectComponent() {}

    protected abstract fun setupNavigatorHolder()


    final override fun onCreate(savedInstanceState: Bundle?) {
        onCreateWithoutInjections()

        injectComponent()
        setupNavigatorHolderWithStateChange()

        onCreateWithoutPresenter()

        super.onCreate(savedInstanceState)

        onCreateInitialized(savedInstanceState)
    }

    override fun onResume() {
        takeIf { !isNavigatorWasSetup }?.setupNavigatorHolderWithStateChange()
        super.onResume()
    }

    override fun onStart() {
        takeIf { !isNavigatorWasSetup }?.setupNavigatorHolderWithStateChange()
        super.onStart()
    }


    override fun onPause() {
        isNavigatorWasSetup = false
        super.onPause()
    }


    override fun onDestroy() {
        super.onDestroy()
        rejectComponent()
    }

    protected open fun onCreateWithoutPresenter() {}
    protected open fun onCreateWithoutInjections() {}
    protected open fun onCreateInitialized(savedInstanceState: Bundle?) {}


    private fun setupNavigatorHolderWithStateChange() {
        setupNavigatorHolder()
        isNavigatorWasSetup = true
    }




}