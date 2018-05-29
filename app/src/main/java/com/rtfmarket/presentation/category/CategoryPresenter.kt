package com.rtfmarket.presentation.category

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.rtfmarket.common.constants.Screens
import com.rtfmarket.domain.interactor.CategoryInteractor
import com.rtfmarket.domain.model.Category
import com.rtfmarket.domain.model.Product
import com.rtfmarket.ui.bottomnavigation.category.ProductItem
import com.rtfmarket.ui.bottomnavigation.navigation.BottomNavigationTabRouter
import io.reactivex.rxkotlin.subscribeBy

@InjectViewState
class CategoryPresenter(private val category: Category,
                        private val categoryInteractor: CategoryInteractor,
                        private val tabRouter: BottomNavigationTabRouter): MvpPresenter<CategoryView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        categoryInteractor.getCategoryProducts(category.slug)
                .subscribeBy(
                        onSuccess = this::onCategoryLoaded,
                        onError = this::onCategoryLoadingError
                )
    }


    fun onProductClick(product: Product): Boolean {
        return tabRouter.navigateTo(Screens.PRODUCT.screenName).let { true }
    }

    private fun onCategoryLoaded(category: Category) {
        viewState.showProducts(category.products!!.map { ProductItem(it) })
    }

    private fun onCategoryLoadingError(error: Throwable) {
        error.printStackTrace()
    }

}