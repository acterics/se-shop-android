package com.rtfmarket.common.extension.rx

import com.rtfmarket.common.exception.ValidationError
import io.reactivex.SingleObserver
import io.reactivex.SingleOperator
import io.reactivex.disposables.Disposable

class SingleValidationOperator<T>(private val exception: Throwable?,
                                  private val predicate: T.() -> Boolean): SingleOperator<T, T> {

    override fun apply(observer: SingleObserver<in T>): SingleObserver<in T> {
        return object: SingleObserver<T> {
            override fun onSubscribe(d: Disposable) {
                observer.onSubscribe(d)
            }

            override fun onError(e: Throwable) {
                observer.onError(e)
            }

            override fun onSuccess(data: T) {
                if (data.predicate()) {
                    observer.onSuccess(data)
                } else {
                    observer.onError(exception ?: ValidationError("Unknown validation error"))
                }
            }

        }
    }
}