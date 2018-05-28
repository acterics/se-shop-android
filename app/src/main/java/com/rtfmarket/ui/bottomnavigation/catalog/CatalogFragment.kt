package com.rtfmarket.ui.bottomnavigation.catalog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rtfmarket.R
import com.rtfmarket.common.BaseFragment
import com.rtfmarket.common.extension.dsl.lazySubcomponent
import com.rtfmarket.di.bottomnavigationtab.BottomNavigationTabComponent
import com.rtfmarket.di.catalog.CatalogComponent
import com.rtfmarket.di.catalog.CatalogModule
import com.rtfmarket.domain.interactor.CatalogInteractor
import com.rtfmarket.ui.bottomnavigation.BottomNavigationTabFragment.Companion.EXTRA_TAB_NAME
import javax.inject.Inject

class CatalogFragment: BaseFragment() {

    companion object {
        fun createInstance(parentComponentName: String): CatalogFragment {
            val args = Bundle()
            args.putString(EXTRA_TAB_NAME, parentComponentName)
            return CatalogFragment().also { it.arguments = args }
        }
    }

    private val tabName by lazy { arguments!!.getString(EXTRA_TAB_NAME) }
    private var component by lazySubcomponent<CatalogComponent, BottomNavigationTabComponent>(
            CatalogComponent.NAME, { tabName }
    ) {
        catalogComponentBuilder()
                .requestCatalogModule(CatalogModule())
                .build()
    }


    @Inject lateinit var catalogInteractor: CatalogInteractor

    override fun injectComponent() { component?.inject(this) }
    override fun rejectComponent() { component = null }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_catalog, container, false)
    }
}