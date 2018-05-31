package com.rtfmarket.domain.repository

import io.reactivex.Completable

interface UserRepository {


    fun isAuthorized(): Completable
}