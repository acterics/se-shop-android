package com.rtfmarket.ui.bottomnavigation.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.rtfmarket.R
import com.rtfmarket.common.BackPressListener
import com.rtfmarket.common.BaseFragment
import com.rtfmarket.common.extension.dsl.lazySubcomponent
import com.rtfmarket.di.bottomnavigationtab.BottomNavigationTabComponent
import com.rtfmarket.di.profileholder.ProfileHolderComponent
import com.rtfmarket.di.profileholder.ProfileHolderModule
import com.rtfmarket.domain.interactor.ProfileInteractor
import com.rtfmarket.presentation.profileholder.ProfileHolderPresenter
import com.rtfmarket.presentation.profileholder.ProfileHolderView
import com.rtfmarket.ui.bottomnavigation.BottomNavigationTabFragment.Companion.EXTRA_TAB_NAME
import ru.terrakok.cicerone.Cicerone
import javax.inject.Inject

class ProfileHolderFragment: BaseFragment(), ProfileHolderView, BackPressListener {

    companion object {
        fun createInstance(parentComponentName: String): BaseFragment {
            return ProfileHolderFragment().also {
                it.arguments = Bundle().also { args ->
                    args.putString(EXTRA_TAB_NAME, parentComponentName)
                }
            }
        }
    }

    private val tabName by lazy { arguments!!.getString(EXTRA_TAB_NAME) }

    private var component by lazySubcomponent<ProfileHolderComponent, BottomNavigationTabComponent>(
            ProfileHolderComponent.NAME,
            { tabName }
    ) {
        profileHolderComponentBuilder()
                .requestProfileModule(ProfileHolderModule(this@ProfileHolderFragment))
                .build()
    }

    @Inject lateinit var profileInteractor: ProfileInteractor
    @Inject lateinit var profileRouter: ProfileHolderRouter
    @Inject lateinit var cicerone: Cicerone<ProfileHolderRouter>
    @Inject lateinit var navigator: ProfileHolderNavigator

    @InjectPresenter lateinit var presenter: ProfileHolderPresenter
    @ProvidePresenter fun providePresenter(): ProfileHolderPresenter {
        return ProfileHolderPresenter(
                profileInteractor,
                profileRouter
        )
    }

    override fun injectComponent() { component?.inject(this) }
    override fun rejectComponent() { component = null }
    override fun setupNavigator() { cicerone.navigatorHolder.setNavigator(navigator) }
    override fun removeNavigator() { cicerone.navigatorHolder.removeNavigator() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_profile_holder, container, false)
    }

    override fun onBackPressed(): Boolean {
        val backPressListenerFragment = childFragmentManager.findFragmentById(R.id.holderProfile) as? BackPressListener
        val isBackPressConsumed = backPressListenerFragment?.onBackPressed() ?: false
        if (!isBackPressConsumed) {
            profileRouter.exit()
        }
        return true
    }
}