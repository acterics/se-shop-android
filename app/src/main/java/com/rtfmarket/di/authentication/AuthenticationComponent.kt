package com.rtfmarket.di.authentication

import com.rtfmarket.di.scope.AuthenticationScope
import com.rtfmarket.ui.authorization.AuthenticationFragment
import com.rtfmarket.ui.authorization.SignInFragment
import com.rtfmarket.ui.authorization.SignUpFragment
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
    fun inject(signInFragment: SignInFragment)
    fun inject(signUpFragment: SignUpFragment)

}