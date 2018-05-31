package com.rtfmarket.ui.bottomnavigation.cart

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter
import com.rtfmarket.R
import com.rtfmarket.common.BaseFragment
import com.rtfmarket.common.extension.clickEventHook
import com.rtfmarket.common.extension.dsl.lazySubcomponent
import com.rtfmarket.common.fastadapter.DefaultItem
import com.rtfmarket.di.bottomnavigationtab.BottomNavigationTabComponent
import com.rtfmarket.di.cart.CartComponent
import com.rtfmarket.di.cart.CartModule
import com.rtfmarket.domain.interactor.CartInteractor
import com.rtfmarket.presentation.cart.CartPresenter
import com.rtfmarket.presentation.cart.CartView
import com.rtfmarket.ui.bottomnavigation.BottomNavigationTabFragment.Companion.EXTRA_TAB_NAME
import kotlinx.android.synthetic.main.fragment_cart.*
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class CartFragment: BaseFragment(), CartView {


    companion object {
        fun createInstance(parentComponentName: String): CartFragment {
            val args = Bundle()
            args.putString(EXTRA_TAB_NAME, parentComponentName)
            return CartFragment().also { it.arguments = args }
        }
    }

    private val tabName by lazy { arguments!!.getString(EXTRA_TAB_NAME) }

    private var component by lazySubcomponent<CartComponent, BottomNavigationTabComponent>(
            CartComponent.NAME, { tabName }
    ) {
        cartComponentBuilder()
                .requestCartComponent(CartModule())
                .build()
    }


    private val cartAdapter = FastItemAdapter<DefaultItem>()


    @Inject lateinit var cartInteractor: CartInteractor
    @Inject lateinit var globarRouter: Router

    @InjectPresenter lateinit var presenter: CartPresenter
    @ProvidePresenter fun providePresenter(): CartPresenter {
        return CartPresenter(cartInteractor, globarRouter)
    }

    override fun injectComponent() {
        component?.inject(this)
    }

    override fun rejectComponent() {
        component = null
    }

    override fun onCreateInitialized(savedInstanceState: Bundle?) {
        super.onCreateInitialized(savedInstanceState)
        cartAdapter.withEventHook(
                clickEventHook<CartItem>(R.id.imClose) { v, position, adapter, item ->
                    presenter.onRemove(item.cartProduct, position)
                }
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvCart.layoutManager = LinearLayoutManager(context)
        rvCart.adapter = cartAdapter

        btCheckout.setOnClickListener { presenter.onStartOrder() }
        holderSwipeToRefresh.setOnRefreshListener {
            presenter.loadCart()
        }
    }

    override fun showCart(items: List<CartItem>) {
        holderSwipeToRefresh.isRefreshing = false
        cartAdapter.set(items)
    }

    override fun removeItem(position: Int) {
        cartAdapter.remove(position)
    }
}