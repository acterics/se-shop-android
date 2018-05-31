package com.rtfmarket.domain.repository

import com.rtfmarket.domain.model.CartProduct
import io.reactivex.Completable
import io.reactivex.Single

interface CartRepository {

    fun getCart(): Single<List<CartProduct>>
    fun addToCart(slug: String): Completable
    fun changeCount(slug: String, count: Int): Completable
    fun removeCount(slug: String): Completable
}