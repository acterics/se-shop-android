package com.rtfmarket.di.product

import com.rtfmarket.di.scope.FragmentScope
import com.rtfmarket.ui.bottomnavigation.product.ProductFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [
    ProductModule::class
])
interface ProductComponent {

    companion object {
        const val NAME = "com.rtfmarket.di.product.NAME"
    }

    @Subcomponent.Builder
    interface Builder {
        fun requestProductModule(productModule: ProductModule): Builder
        fun build(): ProductComponent
    }

    fun inject(productFragment: ProductFragment)
}