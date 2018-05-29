package com.rtfmarket.presentation.category

import com.arellomobile.mvp.MvpView
import com.rtfmarket.ui.bottomnavigation.category.ProductItem

interface CategoryView: MvpView {
    fun showProducts(items: List<ProductItem>)

}