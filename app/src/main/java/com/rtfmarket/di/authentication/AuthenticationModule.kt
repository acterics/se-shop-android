package com.rtfmarket.di.authentication

import com.rtfmarket.di.scope.AuthenticationScope
import com.rtfmarket.domain.interactor.AuthenticationInteractor
import com.rtfmarket.domain.interactor.AuthenticationInteractorImpl
import dagger.Module
import dagger.Provides

@Module
class AuthenticationModule {

    @AuthenticationScope
    @Provides
    fun provideAuthenticationInteractor(authenticationInteractor: AuthenticationInteractorImpl): AuthenticationInteractor {
        return authenticationInteractor
    }
}