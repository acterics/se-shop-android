package com.rtfmarket.ui.bottomnavigation.product

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.bumptech.glide.Glide
import com.rtfmarket.R
import com.rtfmarket.common.BaseFragment
import com.rtfmarket.common.extension.dsl.lazySubcomponent
import com.rtfmarket.di.bottomnavigationtab.BottomNavigationTabComponent
import com.rtfmarket.di.product.ProductComponent
import com.rtfmarket.di.product.ProductModule
import com.rtfmarket.domain.interactor.ProductInteractor
import com.rtfmarket.domain.model.Product
import com.rtfmarket.domain.model.ProductDetails
import com.rtfmarket.presentation.product.ProductPresenter
import com.rtfmarket.presentation.product.ProductView
import com.rtfmarket.ui.bottomnavigation.BottomNavigationTabFragment.Companion.EXTRA_TAB_NAME
import com.rtfmarket.ui.bottomnavigation.navigation.BottomNavigationTabRouter
import kotlinx.android.synthetic.main.fragment_product.*
import javax.inject.Inject

class ProductFragment: BaseFragment(), ProductView {

    companion object {
        const val EXTRA_PRODUCT = "EXTRA_PRODUCT"

        fun createInstance(parentComponentName: String,
                           product: Product): ProductFragment {
            return ProductFragment().also {
                it.arguments = Bundle().also { args ->
                    args.putString(EXTRA_TAB_NAME, parentComponentName)
                    args.putParcelable(EXTRA_PRODUCT, product)
                }
            }
        }
    }


    private val product by lazy { arguments!!.getParcelable<Product>(EXTRA_PRODUCT) }
    private val tabName by lazy { arguments!!.getString(EXTRA_TAB_NAME) }

    private var component by lazySubcomponent<ProductComponent, BottomNavigationTabComponent>(
            ProductComponent.NAME, { tabName }
    ) {
        productComponentBuilder()
                .requestProductModule(ProductModule())
                .build()
    }


    @Inject lateinit var productInteractor: ProductInteractor
    @Inject lateinit var tabRouter: BottomNavigationTabRouter

    @InjectPresenter lateinit var presenter: ProductPresenter
    @ProvidePresenter fun providePresenter(): ProductPresenter {
        return ProductPresenter(product, productInteractor, tabRouter)
    }

    override fun injectComponent() { component?.inject(this) }
    override fun rejectComponent() { component = null }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_product, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(context)
                .load(product.media.main)
                .centerCrop()
                .into(imProduct)
        collapsingToolbarLayout.title = product.title
        tvDescription.text = product.description
        tvPrice.text = "${product.price} UAH"
        btAddToCart.setOnClickListener { presenter.onAddToCart() }
    }

    override fun showProductDetails(productDetails: ProductDetails) {
        vRating.rating = productDetails.rating
    }
}