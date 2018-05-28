package com.rtfmarket.domain.interactor

import com.rtfmarket.domain.executor.ExecutionScheduler
import com.rtfmarket.domain.model.Category
import com.rtfmarket.domain.repository.ProductRepository
import io.reactivex.Single
import javax.inject.Inject

interface CatalogInteractor {

    fun getCategories(): Single<List<Category>>
}

class CatalogInteractorImpl
@Inject constructor(private val productRepository: ProductRepository,
                    private val executionScheduler: ExecutionScheduler): CatalogInteractor {
    override fun getCategories(): Single<List<Category>> {
        return productRepository.getCategories()
                .compose(executionScheduler.highPrioritySingle())
    }
}