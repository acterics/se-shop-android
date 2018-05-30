package com.rtfmarket.domain.interactor

import com.rtfmarket.domain.executor.ExecutionScheduler
import com.rtfmarket.domain.model.ProductDetails
import com.rtfmarket.domain.model.Review
import com.rtfmarket.domain.repository.ProductRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

interface ProductInteractor {
    fun getProductDetails(productSlug: String): Single<ProductDetails>
    fun addProductToCart(productSlug: String): Completable
    fun addReview(productSlug: String, review: Review): Completable
}

class ProductInteractorImpl
@Inject constructor(private val productRepository: ProductRepository,
                    private val executionScheduler: ExecutionScheduler): ProductInteractor {
    override fun getProductDetails(productSlug: String): Single<ProductDetails> {
        return productRepository.getProductDetails(productSlug)
                .compose(executionScheduler.highPrioritySingle())
    }


    override fun addProductToCart(productSlug: String): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addReview(productSlug: String, review: Review): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}