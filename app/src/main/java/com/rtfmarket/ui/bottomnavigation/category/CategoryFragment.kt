package com.rtfmarket.ui.bottomnavigation.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rtfmarket.R
import com.rtfmarket.common.BaseFragment
import com.rtfmarket.domain.model.Category
import com.rtfmarket.ui.bottomnavigation.BottomNavigationTabFragment.Companion.EXTRA_TAB_NAME

class CategoryFragment: BaseFragment() {
    companion object {
        const val EXTRA_CATEGORY = "EXTRA_CATEGORY"

        fun createInstance(parentComponentName: String,
                           category: Category): CategoryFragment {
            return CategoryFragment().also {
                it.arguments = Bundle().also { args ->
                    args.putString(EXTRA_TAB_NAME, parentComponentName)
                    args.putParcelable(EXTRA_CATEGORY, category)
                }
            }
        }
    }

    private val tabName by lazy { arguments!!.getString(EXTRA_TAB_NAME) }
    private val category by lazy { arguments!!.getParcelable<Category>(EXTRA_CATEGORY) }

    override fun injectComponent() {}

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_category, container, false)
    }
}