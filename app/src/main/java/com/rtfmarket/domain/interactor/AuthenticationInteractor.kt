package com.rtfmarket.domain.interactor

import com.rtfmarket.domain.executor.ExecutionScheduler
import com.rtfmarket.domain.repository.UserRepository
import io.reactivex.Completable
import javax.inject.Inject

interface AuthenticationInteractor {
    fun signIn(email: String, password: String): Completable
}

class AuthenticationInteractorImpl
@Inject constructor(private val userRepository: UserRepository,
                    private val executionScheduler: ExecutionScheduler):
        AuthenticationInteractor {
    override fun signIn(email: String, password: String): Completable {
        return userRepository.signIn(email, password)
                .compose(executionScheduler.highPriorityCompletable())
    }
}