package com.rtfmarket.di.authentication

import com.rtfmarket.di.scope.AuthenticationScope
import com.rtfmarket.ui.authorization.AuthenticationFragment
import dagger.Subcomponent

@AuthenticationScope
@Subcomponent(modules = [
    AuthenticationModule::class
])
interface AuthenticationComponent {

    companion object {
        const val NAME = "com.rtfmarket.di.authentication.AuthenticationComponent"
    }

    @Subcomponent.Builder
    interface Builder {
        fun requestAuthenticationModule(authenticationModule: AuthenticationModule): Builder
        fun build(): AuthenticationComponent
    }


    fun inject(authenticationFragment: AuthenticationFragment)

}