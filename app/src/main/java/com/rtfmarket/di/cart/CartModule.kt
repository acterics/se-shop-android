package com.rtfmarket.di.cart

import com.rtfmarket.di.scope.FragmentScope
import com.rtfmarket.domain.interactor.CartInteractor
import com.rtfmarket.domain.interactor.CartInteractorImpl
import dagger.Module
import dagger.Provides

@Module
class CartModule {

    @FragmentScope
    @Provides
    fun provideCartInteractor(cartInteractor: CartInteractorImpl): CartInteractor {
        return cartInteractor
    }
}