package com.rtfmarket.presentation.product

import com.arellomobile.mvp.MvpView
import com.rtfmarket.domain.model.ProductDetails

interface ProductView: MvpView {
    fun showProductDetails(productDetails: ProductDetails)

}