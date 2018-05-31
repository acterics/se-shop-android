package com.rtfmarket.di.ordering

import com.rtfmarket.di.scope.OrderingScope
import com.rtfmarket.ui.ordering.OrderingFragment
import dagger.Subcomponent

@OrderingScope
@Subcomponent(modules = [
    OrderingModule::class
])
interface OrderingComponent {

    companion object {
        const val NAME = "com.rtfmarket.di.ordering.OrderingComponent"
    }

    @Subcomponent.Builder
    interface Builder {
        fun requestOrderingModule(orderingModule: OrderingModule): Builder
        fun build(): OrderingComponent

    }

    fun inject(orderingFragment: OrderingFragment)

}