package com.rtfmarket.domain.repository

import com.rtfmarket.domain.model.Category
import com.rtfmarket.domain.model.Product
import com.rtfmarket.domain.model.ProductDetails
import io.reactivex.Single

interface ProductRepository {



    fun getCategory(categorySlug: String,
                    filter: List<String>,
                    sort: String,
                    order: String): Single<Category>
    fun getCategories(): Single<List<Category>>

    fun getProduct(productSlug: String): Single<Product>
    fun getProductDetails(productSlug: String): Single<ProductDetails>
}