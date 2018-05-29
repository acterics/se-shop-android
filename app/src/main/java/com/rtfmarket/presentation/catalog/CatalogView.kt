package com.rtfmarket.presentation.catalog

import com.arellomobile.mvp.MvpView
import com.rtfmarket.ui.bottomnavigation.catalog.CategoryItem

interface CatalogView: MvpView {

    fun showCategories(items: List<CategoryItem>)
}