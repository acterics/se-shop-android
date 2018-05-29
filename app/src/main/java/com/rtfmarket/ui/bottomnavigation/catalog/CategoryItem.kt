package com.rtfmarket.ui.bottomnavigation.catalog

import android.view.View
import com.mikepenz.fastadapter.items.AbstractItem
import com.rtfmarket.R
import com.rtfmarket.common.fastadapter.DefaultViewHolder
import com.rtfmarket.domain.model.Category
import kotlinx.android.synthetic.main.item_category.view.*

class CategoryItem(internal val category: Category): AbstractItem<CategoryItem, DefaultViewHolder>() {
    override fun getType(): Int = R.id.holderItemCategory
    override fun getViewHolder(v: View): DefaultViewHolder = DefaultViewHolder(v)
    override fun getLayoutRes(): Int = R.layout.item_category

    override fun bindView(holder: DefaultViewHolder, payloads: MutableList<Any>) {
        super.bindView(holder, payloads)
        with(holder.itemView) {
            tvTitle.text = category.title
            tvDescription.text = category.description
        }
    }

    override fun unbindView(holder: DefaultViewHolder) {
        super.unbindView(holder)
        with(holder.itemView) {
            tvTitle.text = null
            tvDescription.text = null
        }
    }
}