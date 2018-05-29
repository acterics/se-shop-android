package com.rtfmarket.presentation.catalog

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.rtfmarket.common.constants.Screens
import com.rtfmarket.domain.interactor.CatalogInteractor
import com.rtfmarket.domain.model.Category
import com.rtfmarket.ui.bottomnavigation.catalog.CategoryItem
import com.rtfmarket.ui.bottomnavigation.navigation.BottomNavigationTabRouter
import io.reactivex.rxkotlin.subscribeBy

@InjectViewState
class CatalogPresenter(private val catalogInteractor: CatalogInteractor,
                       private val tabRouter: BottomNavigationTabRouter) :
        MvpPresenter<CatalogView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        catalogInteractor.getCategories()
                .subscribeBy(
                        onSuccess = this::onCategoriesLoaded,
                        onError = this::onCategoryLoadingError
                )
    }

    fun onCategoryClick(category: Category): Boolean {
        return tabRouter.replaceScreen(Screens.CATEGORY.screenName, category).let { true }
    }


    private fun onCategoriesLoaded(categories: List<Category>) {
        viewState.showCategories(categories.map { CategoryItem(it) })
    }

    private fun onCategoryLoadingError(error: Throwable) {
        error.printStackTrace()
    }

}