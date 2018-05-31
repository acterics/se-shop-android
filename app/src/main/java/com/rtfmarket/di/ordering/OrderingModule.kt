package com.rtfmarket.di.ordering

import com.rtfmarket.di.scope.OrderingScope
import com.rtfmarket.ui.ordering.OrderingFragment
import com.rtfmarket.ui.ordering.OrderingNavigator
import com.rtfmarket.ui.ordering.OrderingRouter
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router


@Module
class OrderingModule(private val orderingFragment: OrderingFragment) {


    @OrderingScope
    @Provides
    fun provideCicerone(): Cicerone<OrderingRouter> {
        return Cicerone.create(OrderingRouter())
    }

    @OrderingScope
    @Provides
    fun provideRouter(cicerone: Cicerone<OrderingRouter>): OrderingRouter {
        return cicerone.router
    }

    @OrderingScope
    @Provides
    fun provideNavigator(router: Router): OrderingNavigator {
        return OrderingNavigator(orderingFragment, router)
    }



}