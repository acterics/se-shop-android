package com.rtfmarket.di.catalog

import com.rtfmarket.di.scope.FragmentScope
import com.rtfmarket.domain.interactor.CatalogInteractor
import com.rtfmarket.domain.interactor.CatalogInteractorImpl
import dagger.Module
import dagger.Provides

@Module
class CatalogModule {

    @FragmentScope
    @Provides
    fun provideCatalogInteractor(catalogInteractorImpl: CatalogInteractorImpl): CatalogInteractor {
        return catalogInteractorImpl
    }
}