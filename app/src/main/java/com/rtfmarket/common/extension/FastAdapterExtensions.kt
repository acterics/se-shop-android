package com.rtfmarket.common.extension

import android.support.annotation.IdRes
import android.support.v7.widget.RecyclerView
import android.view.View
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter
import com.mikepenz.fastadapter.listeners.ClickEventHook
import com.mikepenz.fastadapter.listeners.EventHook
import com.mikepenz.fastadapter.listeners.LongClickEventHook
import com.mikepenz.fastadapter.select.SelectExtension
import com.rtfmarket.common.fastadapter.DefaultItem

inline fun <reified T: DefaultItem>radioButtonEventHook(@IdRes bindViewId: Int? = null,
                                                crossinline filter: T.(T?) -> Boolean = { true }) =
        object: ClickEventHook<DefaultItem>() {
            override fun onBind(viewHolder: RecyclerView.ViewHolder?): View? {
                return bindViewId?.let { id ->
                    viewHolder?.itemView?.findViewById<View>(id)
                } ?: viewHolder?.itemView
            }

            override fun onClick(v: View, position: Int, fastAdapter: FastAdapter<DefaultItem>, item: DefaultItem) {
                if (!item.isSelected && item.isSelectable) {
                    val selectionExtension = fastAdapter.selectionExtension()
                    val selections = selectionExtension.selections.filter {
                        (fastAdapter.getItem(it) as? T?)?.let { item ->
                            item.isEnabled && item.filter(item as? T?)
                        } ?: false
                    }
                    if (!selections.isEmpty()) {
                        val selectedPosition = selections.iterator().next()
                        selectionExtension.deselect(selections)
                        fastAdapter.notifyItemChanged(selectedPosition)
                    }
                    selectionExtension.select(position)
                }
            }

        }

inline fun <reified T: DefaultItem>clickEventHook(
        @IdRes viewId: Int,
        crossinline onClickCallback: (v: View, position: Int, adapter: FastAdapter<*>, item: T) -> Unit
): EventHook<DefaultItem> {
    return object: ClickEventHook<DefaultItem>() {
        override fun onBind(viewHolder: RecyclerView.ViewHolder?): View? {
            return viewId.let { id ->
                viewHolder?.itemView?.findViewById(id)
            }
        }

        override fun onClick(v: View, position: Int, fastAdapter: FastAdapter<DefaultItem>, item: DefaultItem) {
            (item as? T)?.let { onClickCallback(v, position, fastAdapter, it) }
        }
    }
}

inline fun <reified T: DefaultItem>longClickEventHook(
        @IdRes viewId: Int? = null,
        crossinline onLongClickCallback: (v: View, position: Int, adapter: FastAdapter<*>, item: T?) -> Boolean
): EventHook<DefaultItem> {
    return object: LongClickEventHook<DefaultItem>() {
        override fun onBind(viewHolder: RecyclerView.ViewHolder?): View? {
            return viewId?.let { id ->
                viewHolder?.itemView?.findViewById<View>(id)
            } ?: viewHolder?.itemView
        }

        override fun onLongClick(v: View, position: Int, fastAdapter: FastAdapter<DefaultItem>, item: DefaultItem): Boolean {
            return onLongClickCallback(v, position, fastAdapter, item as? T)
        }
    }
}

fun FastAdapter<*>.selectionExtension(): SelectExtension<*> {
    return extensions.filterIsInstance<SelectExtension<*>>().first()
}

inline fun <reified T: DefaultItem> ItemAdapter<DefaultItem>.adapterItems(): List<T> {
    return adapterItems.filterIsInstance<T>()
}

inline fun <reified T: DefaultItem> FastItemAdapter<DefaultItem>.adapterItems(): List<T> {
    return adapterItems.filterIsInstance<T>()
}

inline fun <reified T: DefaultItem> FastItemAdapter<DefaultItem>.selectFirst(predicate: T.() -> Boolean) {
    adapterItems<T>().firstOrNull { it.predicate() }
            ?.let { getAdapterPosition(it) }
            ?.let { selectionExtension().select(it) }
}
