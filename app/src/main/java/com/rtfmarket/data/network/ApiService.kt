package com.rtfmarket.data.network

import com.rtfmarket.data.network.model.BaseResponse
import com.rtfmarket.domain.model.CartProduct
import com.rtfmarket.domain.model.Category
import com.rtfmarket.domain.model.Product
import com.rtfmarket.domain.model.ProductDetails
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.*
import javax.annotation.Detainted

interface ApiService {

    @GET("products/category/{slug}")
    fun getCategory(@Path("slug") categorySlug: String,
                    @Query("filter") filter: List<String>,
                    @Query("sort") sort: String,
                    @Query("order") order: String): Single<BaseResponse<Category>>

    @GET("categories")
    fun getCategories(): Single<BaseResponse<List<Category>>>

    @GET("products/{slug}/details")
    fun getProductDetails(@Path("slug") productSlug: String): Single<BaseResponse<ProductDetails>>

    @GET("products/{slug}")
    fun getProduct(@Path("slug") productSlug: String): Single<BaseResponse<Product>>

    @GET("cart")
    fun getCart(): Single<BaseResponse<List<CartProduct>>>

    @POST("cart/product/{slug}")
    fun addProductToCart(@Path("slug") slug: String): Completable

    @PUT("cart/product/{slug}")
    fun changeProductCount(@Path("slug") slug: String,
                           @Query("count") count: Int): Completable

    @DELETE("cart/product/{slug}")
    fun deleteProductFromCart(@Path("slug") slug: String): Completable
}