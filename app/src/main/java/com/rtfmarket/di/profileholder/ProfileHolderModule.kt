package com.rtfmarket.di.profileholder

import com.rtfmarket.di.scope.ProfileHolderScope
import com.rtfmarket.domain.interactor.ProfileInteractor
import com.rtfmarket.domain.interactor.ProfileInteractorImpl
import com.rtfmarket.ui.bottomnavigation.navigation.BottomNavigationTabRouter
import com.rtfmarket.ui.bottomnavigation.profile.ProfileHolderFragment
import com.rtfmarket.ui.bottomnavigation.profile.ProfileHolderNavigator
import com.rtfmarket.ui.bottomnavigation.profile.ProfileHolderRouter
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone

@Module
class ProfileHolderModule(private val profileHolderFragment: ProfileHolderFragment) {

    @ProfileHolderScope
    @Provides
    fun provideProfileInteractor(profileInteractor: ProfileInteractorImpl): ProfileInteractor {
        return profileInteractor
    }


    @ProfileHolderScope
    @Provides
    fun provideCicerone(): Cicerone<ProfileHolderRouter> = Cicerone.create(ProfileHolderRouter())

    @ProfileHolderScope
    @Provides
    fun provideRouter(cicerone: Cicerone<ProfileHolderRouter>): ProfileHolderRouter {
        return cicerone.router
    }

    @ProfileHolderScope
    @Provides
    fun provideNavigator(tabRouter: BottomNavigationTabRouter): ProfileHolderNavigator {
        return ProfileHolderNavigator(profileHolderFragment, tabRouter)
    }


}