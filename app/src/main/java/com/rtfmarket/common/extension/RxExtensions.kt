package com.rtfmarket.common.extension

import com.rtfmarket.common.exception.ValidationError
import com.rtfmarket.common.extension.rx.SingleValidationOperator
import com.rtfmarket.common.extension.rx.SingleVerificationOperator
import com.rtfmarket.data.network.model.BaseResponse
import io.reactivex.Flowable
import io.reactivex.Single

fun <F, L, T> Single<L>.mapList(transform: (input: F) -> T): Single<List<T>>
        where L: List<F> {

    return this.map { it.map(transform) }
}

fun <F, L, T> Flowable<L>.mapList(transform: (input: F) -> T): Flowable<List<T>>
        where L: List<F> {

    return this.map { it.map(transform) }
}

fun <T> Single<BaseResponse<T>>.verify(): Single<T> {
    return lift(SingleVerificationOperator())
}

fun <T> Single<T>.validate(exception: Throwable? = null,
                           predicate: T.() -> Boolean): Single<T> {
    return lift(SingleValidationOperator(exception, predicate))
}


