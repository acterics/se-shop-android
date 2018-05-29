package com.rtfmarket.di.category

import com.rtfmarket.di.scope.FragmentScope
import com.rtfmarket.ui.bottomnavigation.category.CategoryFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [
    CategoryModule::class
])
interface CategoryComponent {

    companion object {
        const val NAME = "com.rtfmarket.di.category.NAME"
    }

    @Subcomponent.Builder
    interface Builder {
        fun requestCategoryModule(categoryModule: CategoryModule): Builder
        fun build(): CategoryComponent
    }

    fun inject(categoryFragment: CategoryFragment)
}