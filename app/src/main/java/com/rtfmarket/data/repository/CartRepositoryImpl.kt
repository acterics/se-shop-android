package com.rtfmarket.data.repository

import com.rtfmarket.common.extension.verify
import com.rtfmarket.data.network.ApiService
import com.rtfmarket.domain.executor.ExecutionScheduler
import com.rtfmarket.domain.model.CartProduct
import com.rtfmarket.domain.repository.CartRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class CartRepositoryImpl
@Inject constructor(private val apiService: ApiService):
        CartRepository {
    override fun getCart(): Single<List<CartProduct>> {
        return apiService.getCart().verify()
    }

    override fun addToCart(slug: String): Completable {
        return apiService.addProductToCart(slug)
    }

    override fun changeCount(slug: String, count: Int): Completable {
        return apiService.changeProductCount(slug, count)
    }

    override fun removeCount(slug: String): Completable {
        return apiService.deleteProductFromCart(slug)
    }
}