package com.rtfmarket.domain.interactor

import com.rtfmarket.domain.executor.ExecutionScheduler
import com.rtfmarket.domain.model.CartProduct
import com.rtfmarket.domain.repository.CartRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

interface CartInteractor {
    fun getCart(): Single<List<CartProduct>>
    fun removeFromCart(productSlug: String): Completable
    fun changeProductCount(productSlug: String, newCount: Int): Completable
}


class CartInteractorImpl
@Inject constructor(private val cartRepository: CartRepository,
                    private val executionScheduler: ExecutionScheduler):
        CartInteractor {


    override fun getCart(): Single<List<CartProduct>> {
        return cartRepository.getCart()
                .compose(executionScheduler.highPrioritySingle())
    }

    override fun removeFromCart(productSlug: String): Completable {
        return cartRepository.removeCount(productSlug)
                .compose(executionScheduler.highPriorityCompletable())
    }

    override fun changeProductCount(productSlug: String, newCount: Int): Completable {
        return cartRepository.changeCount(productSlug, newCount)
                .compose(executionScheduler.highPriorityCompletable())
    }
}