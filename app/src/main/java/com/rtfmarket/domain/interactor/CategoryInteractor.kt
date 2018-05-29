package com.rtfmarket.domain.interactor

import com.rtfmarket.common.extension.validate
import com.rtfmarket.domain.executor.ExecutionScheduler
import com.rtfmarket.domain.model.Category
import com.rtfmarket.domain.repository.ProductRepository
import io.reactivex.Single
import javax.inject.Inject

interface CategoryInteractor {
    fun getCategoryProducts(slug: String,
                            filter: List<String> = listOf(),
                            sort: String = "",
                            order: String = ""): Single<Category>
}


class CategoryInteractorImpl
@Inject constructor(private val productRepository: ProductRepository,
                    private val executionScheduler: ExecutionScheduler): CategoryInteractor {
    override fun getCategoryProducts(slug: String,
                                     filter: List<String>,
                                     sort: String,
                                     order: String): Single<Category> {
        return productRepository.getCategory(slug, filter, sort, order)
                .validate { products != null }
                .compose(executionScheduler.highPrioritySingle())
    }
}