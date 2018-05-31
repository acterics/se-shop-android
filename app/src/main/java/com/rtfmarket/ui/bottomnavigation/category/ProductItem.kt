package com.rtfmarket.ui.bottomnavigation.category

import android.annotation.SuppressLint
import android.view.View
import com.bumptech.glide.Glide
import com.mikepenz.fastadapter.items.AbstractItem
import com.rtfmarket.R
import com.rtfmarket.common.fastadapter.DefaultViewHolder
import com.rtfmarket.domain.model.Product
import kotlinx.android.synthetic.main.item_product.view.*

class ProductItem(internal val product:Product): AbstractItem<ProductItem, DefaultViewHolder>() {

    override fun getType(): Int = R.id.holderItemProduct
    override fun getViewHolder(v: View): DefaultViewHolder = DefaultViewHolder(v)
    override fun getLayoutRes(): Int = R.layout.item_product

    @SuppressLint("SetTextI18n")
    override fun bindView(holder: DefaultViewHolder, payloads: MutableList<Any>) {
        super.bindView(holder, payloads)
        with(holder.itemView) {
            Glide.with(context)
                    .load(product.media.main)
                    .centerCrop()
                    .into(imProduct)
            tvProductTitle.text = product.title
            tvProductDescription.text = product.description
            tvPrice.text = "${product.price} UAH"
        }
    }

}