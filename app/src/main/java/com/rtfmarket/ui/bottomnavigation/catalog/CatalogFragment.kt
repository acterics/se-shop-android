package com.rtfmarket.ui.bottomnavigation.catalog

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter
import com.rtfmarket.R
import com.rtfmarket.common.BaseFragment
import com.rtfmarket.common.extension.dsl.lazySubcomponent
import com.rtfmarket.di.bottomnavigationtab.BottomNavigationTabComponent
import com.rtfmarket.di.catalog.CatalogComponent
import com.rtfmarket.di.catalog.CatalogModule
import com.rtfmarket.domain.interactor.CatalogInteractor
import com.rtfmarket.presentation.catalog.CatalogPresenter
import com.rtfmarket.presentation.catalog.CatalogView
import com.rtfmarket.ui.bottomnavigation.BottomNavigationTabFragment.Companion.EXTRA_TAB_NAME
import com.rtfmarket.ui.bottomnavigation.navigation.BottomNavigationTabRouter
import kotlinx.android.synthetic.main.fragment_catalog.*
import javax.inject.Inject

class CatalogFragment: BaseFragment(), CatalogView {

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

    private val categoriesAdapter = FastItemAdapter<CategoryItem>()


    @Inject lateinit var catalogInteractor: CatalogInteractor
    @Inject lateinit var tabRouter: BottomNavigationTabRouter

    @InjectPresenter lateinit var presenter: CatalogPresenter
    @ProvidePresenter fun providePresenter(): CatalogPresenter {
        return CatalogPresenter(
                catalogInteractor,
                tabRouter
        )
    }

    override fun injectComponent() { component?.inject(this) }
    override fun rejectComponent() { component = null }

    override fun onCreateInitialized(savedInstanceState: Bundle?) {
        super.onCreateInitialized(savedInstanceState)
        categoriesAdapter.withOnClickListener { _, _, item, _ ->
            presenter.onCategoryClick(item.category)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_catalog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvCategories.layoutManager = GridLayoutManager(context, 2)
        rvCategories.adapter = categoriesAdapter
    }

    override fun showCategories(items: List<CategoryItem>) {
        categoriesAdapter.set(items)
    }
}