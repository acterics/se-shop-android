package com.rtfmarket.di.catalog

import com.rtfmarket.di.scope.FragmentScope
import com.rtfmarket.ui.bottomnavigation.catalog.CatalogFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [
    CatalogModule::class
])
interface CatalogComponent {

    companion object {
        const val NAME = "com.rtfmarket.di.main.CatalogComponent"
    }

    @Subcomponent.Builder
    interface Builder {
        fun requestCatalogModule(catalogModule: CatalogModule): Builder
        fun build(): CatalogComponent
    }

    fun inject(catalogFragment: CatalogFragment)
}