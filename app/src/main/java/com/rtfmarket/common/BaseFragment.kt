package com.rtfmarket.common


import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatFragment
import com.rtfmarket.di.ComponentsManager


abstract class BaseFragment: MvpAppCompatFragment() {

    protected abstract fun injectComponent()
    protected open fun rejectComponent() {}
    protected open fun setupNavigator() {}
    protected open fun removeNavigator() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        onCreateWithoutInjections()
        injectComponent()
        onCreateWithoutPresenter()
        super.onCreate(savedInstanceState)
        onCreateInitialized(savedInstanceState)
    }

    override fun onResume() {
        setupNavigator()
        super.onResume()
    }

    override fun onPause() {
        removeNavigator()
        super.onPause()
    }

    override fun onDestroy() {
        if (isRemoving || activity!!.isFinishing && !isStateSaved) {
            rejectComponent()
        }
        super.onDestroy()
    }

    protected open fun onCreateWithoutPresenter() {}
    protected open fun onCreateWithoutInjections() {}
    protected open fun onCreateInitialized(savedInstanceState: Bundle?) {}

    @Suppress("UNCHECKED_CAST")
    protected fun <T>getComponent(componentName: String): T? {
        return ComponentsManager.components[componentName] as T?
    }


}