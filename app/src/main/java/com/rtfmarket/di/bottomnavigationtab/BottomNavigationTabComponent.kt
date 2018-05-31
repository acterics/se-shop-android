package com.rtfmarket.di.bottomnavigationtab

import com.rtfmarket.di.catalog.CatalogComponent
import com.rtfmarket.di.category.CategoryComponent
import com.rtfmarket.di.product.ProductComponent
import com.rtfmarket.di.profileholder.ProfileHolderComponent
import com.rtfmarket.di.scope.BottomNavigationTabScope
import com.rtfmarket.ui.bottomnavigation.BottomNavigationTabFragment
import dagger.Subcomponent

@BottomNavigationTabScope
@Subcomponent(modules = [
    BottomNavigationTabModule::class
])
interface BottomNavigationTabComponent {

    @Subcomponent.Builder
    interface Builder {
        fun requestBottomNavigationTabModule(bottomNavigationTabModule: BottomNavigationTabModule): Builder
        fun build(): BottomNavigationTabComponent
    }

    fun catalogComponentBuilder(): CatalogComponent.Builder
    fun categoryComponentBuilder(): CategoryComponent.Builder
    fun productComponentBuilder(): ProductComponent.Builder
    fun profileHolderComponentBuilder(): ProfileHolderComponent.Builder

    fun inject(bottomNavigationTabFragment: BottomNavigationTabFragment)
}