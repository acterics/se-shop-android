package com.rtfmarket.di.category

import com.rtfmarket.di.scope.FragmentScope
import com.rtfmarket.domain.interactor.CategoryInteractor
import com.rtfmarket.domain.interactor.CategoryInteractorImpl
import dagger.Module
import dagger.Provides

@Module
class CategoryModule {


    @FragmentScope
    @Provides
    fun provideCategoryInteractor(categoryInteractorImpl: CategoryInteractorImpl): CategoryInteractor {
        return categoryInteractorImpl
    }

}