package com.rtfmarket.presentation.cart

import com.arellomobile.mvp.MvpView
import com.rtfmarket.ui.bottomnavigation.cart.CartItem

interface CartView: MvpView {
    fun showCart(items: List<CartItem>)
    fun removeItem(position: Int)
}