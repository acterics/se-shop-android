package com.rtfmarket.di.product

import com.rtfmarket.di.scope.FragmentScope
import com.rtfmarket.domain.interactor.ProductInteractor
import com.rtfmarket.domain.interactor.ProductInteractorImpl
import dagger.Module
import dagger.Provides

@Module
class ProductModule {

    @FragmentScope
    @Provides
    fun provideProductInteractor(productInteractor: ProductInteractorImpl): ProductInteractor {
        return productInteractor
    }
}