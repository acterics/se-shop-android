package com.rtfmarket.common.fastadapter

import com.mikepenz.fastadapter.IItem
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter
import com.mikepenz.fastadapter.expandable.items.AbstractExpandableItem


typealias DefaultItem = IItem<*, *>
typealias DefaultExpandableItem = AbstractExpandableItem<*, *, *>
typealias DefaultItemAdapter = ItemAdapter<DefaultItem>
typealias DefaultFastItemAdapter = FastItemAdapter<DefaultItem>
