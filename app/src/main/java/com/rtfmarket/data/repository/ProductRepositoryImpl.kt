package com.rtfmarket.data.repository

import com.rtfmarket.common.extension.verify
import com.rtfmarket.data.network.ApiService
import com.rtfmarket.domain.model.Category
import com.rtfmarket.domain.model.Product
import com.rtfmarket.domain.model.ProductDetails
import com.rtfmarket.domain.repository.ProductRepository
import io.reactivex.Single
import javax.inject.Inject

class ProductRepositoryImpl
@Inject constructor(private val apiService: ApiService): ProductRepository {

    override fun getProductDetails(productSlug: String): Single<ProductDetails> {
        return apiService.getProductDetails(productSlug).verify()
    }

    override fun getCategory(categorySlug: String, filter: List<String>, sort: String, order: String): Single<Category> {
        return apiService.getCategory(categorySlug, filter, sort, order).verify()
    }

    override fun getProduct(productSlug: String): Single<Product> {
        return apiService.getProduct(productSlug).verify()
    }

    override fun getCategories(): Single<List<Category>> {
        return apiService.getCategories().verify()
    }
}