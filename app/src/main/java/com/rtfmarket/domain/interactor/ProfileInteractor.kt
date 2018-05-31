package com.rtfmarket.domain.interactor

import com.rtfmarket.domain.executor.ExecutionScheduler
import com.rtfmarket.domain.model.Profile
import com.rtfmarket.domain.repository.UserRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

interface ProfileInteractor {
    fun isAuthorized(): Completable
    fun getProfile(): Single<Profile>
    fun logout(): Completable
}

class ProfileInteractorImpl
@Inject constructor(private val userRepository: UserRepository,
                    private val executionScheduler: ExecutionScheduler): ProfileInteractor {
    override fun isAuthorized(): Completable {
        return userRepository.isAuthorized()
                .compose(executionScheduler.highPriorityCompletable())
    }

    override fun getProfile(): Single<Profile> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun logout(): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}