package com.rtfmarket.di.app

import com.rtfmarket.data.repository.ProductRepositoryImpl
import com.rtfmarket.domain.repository.ProductRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Singleton
    @Provides
    fun provideProductRepository(productRepository: ProductRepositoryImpl): ProductRepository {
        return productRepository
    }
}