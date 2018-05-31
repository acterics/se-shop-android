package com.rtfmarket.di.authentication

import com.rtfmarket.di.scope.AuthenticationScope
import com.rtfmarket.domain.interactor.AuthenticationInteractor
import com.rtfmarket.domain.interactor.AuthenticationInteractorImpl
import com.rtfmarket.ui.authorization.AuthenticationFragment
import com.rtfmarket.ui.authorization.AuthenticationNavigator
import com.rtfmarket.ui.authorization.AuthenticationRouter
import com.rtfmarket.ui.bottomnavigation.navigation.BottomNavigationTabRouter
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router

@Module
class AuthenticationModule(private val authenticationFragment: AuthenticationFragment) {

    @AuthenticationScope
    @Provides
    fun provideAuthenticationInteractor(authenticationInteractor: AuthenticationInteractorImpl): AuthenticationInteractor {
        return authenticationInteractor
    }

    @AuthenticationScope
    @Provides
    fun provideCicerone(): Cicerone<AuthenticationRouter> {
        return Cicerone.create(AuthenticationRouter())
    }

    @AuthenticationScope
    @Provides
    fun provideRouter(cicerone: Cicerone<AuthenticationRouter>): AuthenticationRouter {
        return cicerone.router
    }

    @AuthenticationScope
    @Provides
    fun provideNavigator(router: Router): AuthenticationNavigator {
        return AuthenticationNavigator(authenticationFragment, router)
    }
}