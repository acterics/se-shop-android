package com.rtfmarket.presentation.product

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.rtfmarket.domain.interactor.ProductInteractor
import com.rtfmarket.domain.model.Product
import com.rtfmarket.ui.bottomnavigation.navigation.BottomNavigationTabRouter

@InjectViewState
class ProductPresenter(private val product: Product,
                       private val productInteractor: ProductInteractor,
                       private val tabRouter: BottomNavigationTabRouter):
        MvpPresenter<ProductView>() {

}