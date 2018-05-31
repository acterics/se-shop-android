package com.rtfmarket.data.repository

import android.security.keystore.UserNotAuthenticatedException
import com.rtfmarket.common.exception.UserNotAuthorizedException
import com.rtfmarket.data.network.ApiService
import com.rtfmarket.data.preference.PreferenceSource
import com.rtfmarket.domain.repository.UserRepository
import io.reactivex.Completable
import javax.inject.Inject

class UserRepositoryImpl
@Inject constructor(private val apiService: ApiService,
                    private val preferenceSource: PreferenceSource): UserRepository {

    override fun isAuthorized(): Completable {
        return if (preferenceSource.getAuthToken().isEmpty()) {
            Completable.error(UserNotAuthorizedException())
        } else {
            Completable.complete()
        }
    }

    override fun signIn(email: String, password: String): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}