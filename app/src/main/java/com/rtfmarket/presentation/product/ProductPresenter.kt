package com.rtfmarket.presentation.product

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.rtfmarket.domain.interactor.ProductInteractor
import com.rtfmarket.domain.model.Product
import com.rtfmarket.domain.model.ProductDetails
import com.rtfmarket.ui.bottomnavigation.navigation.BottomNavigationTabRouter
import io.reactivex.rxkotlin.subscribeBy

@InjectViewState
class ProductPresenter(private val product: Product,
                       private val productInteractor: ProductInteractor,
                       private val tabRouter: BottomNavigationTabRouter):
        MvpPresenter<ProductView>() {


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        productInteractor.getProductDetails(product.slug)
                .subscribeBy(
                        onSuccess = this::onProductDetailsLoaded,
                        onError = this::onProductDetailsLoadingError
                )
    }


    private fun onProductDetailsLoaded(productDetails: ProductDetails) {

    }

    private fun onProductDetailsLoadingError(error: Throwable) {

    }

}