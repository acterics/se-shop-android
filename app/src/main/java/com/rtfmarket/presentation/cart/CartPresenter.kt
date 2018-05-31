package com.rtfmarket.presentation.cart

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.rtfmarket.common.constants.Screens
import com.rtfmarket.domain.interactor.CartInteractor
import com.rtfmarket.domain.model.CartProduct
import com.rtfmarket.ui.bottomnavigation.cart.CartItem
import io.reactivex.rxkotlin.subscribeBy
import ru.terrakok.cicerone.Router

@InjectViewState
class CartPresenter(private val cartInteractor: CartInteractor,
                    private val globalRouter: Router):
        MvpPresenter<CartView>() {


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        cartInteractor.getCart().subscribeBy(
                onSuccess = this::onCartLoaded,
                onError = this::onCartLoadingError
        )
    }


    fun onStartOrder() {
        globalRouter.navigateTo(Screens.ORDERING.screenName)
    }

    private fun onCartLoaded(cart: List<CartProduct>) {
        viewState.showCart(cart.map { CartItem(it) })
    }

    private fun onCartLoadingError(error: Throwable) {

    }

    fun onRemove(cartProduct: CartProduct, position: Int) {
        cartInteractor.removeFromCart(cartProduct.product.slug)
                .subscribeBy {
                    viewState.removeItem(position)
                }
    }

}