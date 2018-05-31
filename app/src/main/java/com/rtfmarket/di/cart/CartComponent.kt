package com.rtfmarket.di.cart

import com.rtfmarket.di.scope.FragmentScope
import com.rtfmarket.ui.bottomnavigation.cart.CartFragment
import dagger.Subcomponent


@FragmentScope
@Subcomponent(modules = [
    CartModule::class
])
interface CartComponent {


    companion object {
        const val NAME = "com.rtfmarket.di.cart.CartComponent"
    }

    @Subcomponent.Builder
    interface Builder {
        fun requestCartComponent(cartModule: CartModule): Builder
        fun build(): CartComponent
    }


    fun inject(cartFragment: CartFragment)
}