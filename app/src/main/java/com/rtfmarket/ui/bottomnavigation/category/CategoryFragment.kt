package com.rtfmarket.ui.bottomnavigation.category

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
import com.rtfmarket.di.category.CategoryComponent
import com.rtfmarket.di.category.CategoryModule
import com.rtfmarket.domain.interactor.CategoryInteractor
import com.rtfmarket.domain.model.Category
import com.rtfmarket.presentation.category.CategoryPresenter
import com.rtfmarket.presentation.category.CategoryView
import com.rtfmarket.ui.bottomnavigation.BottomNavigationTabFragment.Companion.EXTRA_TAB_NAME
import com.rtfmarket.ui.bottomnavigation.navigation.BottomNavigationTabRouter
import kotlinx.android.synthetic.main.fragment_category.*
import javax.inject.Inject

class CategoryFragment: BaseFragment(), CategoryView {
    companion object {
        const val EXTRA_CATEGORY = "EXTRA_CATEGORY"

        fun createInstance(parentComponentName: String,
                           category: Category): CategoryFragment {
            return CategoryFragment().also {
                it.arguments = Bundle().also { args ->
                    args.putString(EXTRA_TAB_NAME, parentComponentName)
                    args.putParcelable(EXTRA_CATEGORY, category)
                }
            }
        }
    }

    private var component by lazySubcomponent<CategoryComponent, BottomNavigationTabComponent>(
            CategoryComponent.NAME, { tabName }
    ) {
        categoryComponentBuilder()
                .requestCategoryModule(CategoryModule())
                .build()
    }

    private val tabName by lazy { arguments!!.getString(EXTRA_TAB_NAME) }
    private val category by lazy { arguments!!.getParcelable<Category>(EXTRA_CATEGORY) }

    private val productsAdapter = FastItemAdapter<ProductItem>()

    @Inject lateinit var categoryInteractor: CategoryInteractor
    @Inject lateinit var tabRouter: BottomNavigationTabRouter

    @InjectPresenter lateinit var presenter: CategoryPresenter
    @ProvidePresenter fun providePresenter(): CategoryPresenter {
        return CategoryPresenter(category, categoryInteractor, tabRouter)
    }

    override fun injectComponent() { component?.inject(this) }
    override fun rejectComponent() { component = null }

    override fun onCreateInitialized(savedInstanceState: Bundle?) {
        super.onCreateInitialized(savedInstanceState)
        productsAdapter.withOnClickListener { _, _, item, _ ->
            presenter.onProductClick(item.product)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvProducts.layoutManager = GridLayoutManager(context, 2)
        rvProducts.adapter = productsAdapter
    }

    override fun showProducts(items: List<ProductItem>) {
        productsAdapter.set(items)
    }
}