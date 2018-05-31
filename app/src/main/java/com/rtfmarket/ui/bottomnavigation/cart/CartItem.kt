package com.rtfmarket.ui.bottomnavigation.cart

import android.annotation.SuppressLint
import android.view.View
import com.bumptech.glide.Glide
import com.mikepenz.fastadapter.items.AbstractItem
import com.rtfmarket.R
import com.rtfmarket.common.fastadapter.DefaultViewHolder
import com.rtfmarket.domain.model.CartProduct
import kotlinx.android.synthetic.main.item_cart.view.*

class CartItem(internal val cartProduct: CartProduct):
        AbstractItem<CartItem, DefaultViewHolder>() {
    override fun getType(): Int = R.id.holderCartItem
    override fun getViewHolder(v: View): DefaultViewHolder = DefaultViewHolder(v)
    override fun getLayoutRes(): Int = R.layout.item_cart

    @SuppressLint("SetTextI18n")
    override fun bindView(holder: DefaultViewHolder, payloads: MutableList<Any>) {
        super.bindView(holder, payloads)
        with(holder.itemView) {
            Glide.with(context)
                    .load(cartProduct.product.media.main)
                    .centerCrop()
                    .into(imProduct)
            tvProductTitle.text = cartProduct.product.title
            tvProductPrice.text = "${cartProduct.product.price} UAH"
            tvCount.text = "${cartProduct.count}"
        }
    }
}