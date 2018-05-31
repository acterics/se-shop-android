package com.rtfmarket.ui.ordering

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rtfmarket.R
import com.rtfmarket.common.BackPressListener
import com.rtfmarket.common.BaseFragment
import com.rtfmarket.common.extension.dsl.subcomponent
import com.rtfmarket.di.main.MainComponent
import com.rtfmarket.di.ordering.OrderingComponent
import com.rtfmarket.di.ordering.OrderingModule
import ru.terrakok.cicerone.Cicerone
import javax.inject.Inject

class OrderingFragment: BaseFragment(), BackPressListener {

    private var component by subcomponent<OrderingComponent, MainComponent>(
            OrderingComponent.NAME, MainComponent.NAME
    ) {
        orderingComponentBuilder()
                .requestOrderingModule(OrderingModule(this@OrderingFragment))
                .build()
    }

    @Inject lateinit var orderingRouter: OrderingRouter
    @Inject lateinit var cicerone: Cicerone<OrderingRouter>
    @Inject lateinit var navigator: OrderingNavigator



    override fun injectComponent() { component?.inject(this) }
    override fun rejectComponent() { component = null }
    override fun setupNavigator() { cicerone.navigatorHolder.setNavigator(navigator) }
    override fun removeNavigator() { cicerone.navigatorHolder.removeNavigator() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_ordering, container, false)
    }

    override fun onBackPressed(): Boolean {
        val backPressListenerFragment = childFragmentManager.findFragmentById(R.id.holderOrdering) as? BackPressListener
        val isBackPressConsumed = backPressListenerFragment?.onBackPressed() ?: false
        if (!isBackPressConsumed) {
            orderingRouter.exit()
        }
        return true
    }
}