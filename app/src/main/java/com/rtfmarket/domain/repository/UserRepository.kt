package com.rtfmarket.domain.repository

import io.reactivex.Completable

interface UserRepository {


    fun isAuthorized(): Completable
    fun signIn(email: String, password: String): Completable
}