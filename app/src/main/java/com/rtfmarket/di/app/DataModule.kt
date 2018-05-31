package com.rtfmarket.di.app

import com.rtfmarket.data.repository.CartRepositoryImpl
import com.rtfmarket.data.repository.ProductRepositoryImpl
import com.rtfmarket.data.repository.UserRepositoryImpl
import com.rtfmarket.domain.repository.CartRepository
import com.rtfmarket.domain.repository.ProductRepository
import com.rtfmarket.domain.repository.UserRepository
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

    @Singleton
    @Provides
    fun provideUserRepository(userRepository: UserRepositoryImpl): UserRepository {
        return userRepository
    }

    @Singleton
    @Provides
    fun provideCartRepository(cartRepository: CartRepositoryImpl): CartRepository {
        return cartRepository
    }
}