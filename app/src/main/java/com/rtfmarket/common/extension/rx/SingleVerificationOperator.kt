package com.rtfmarket.common.extension.rx

import com.rtfmarket.data.network.exception.NetworkException
import com.rtfmarket.data.network.model.BaseResponse
import io.reactivex.SingleObserver
import io.reactivex.SingleOperator
import io.reactivex.disposables.Disposable

class SingleVerificationOperator<T>: SingleOperator<T, BaseResponse<T>> {
    override fun apply(observer: SingleObserver<in T>): SingleObserver<in BaseResponse<T>> {
        return object: SingleObserver<BaseResponse<T>> {
            override fun onSubscribe(d: Disposable) {
                observer.onSubscribe(d)
            }

            override fun onError(e: Throwable) {
                observer.onError(e)
            }

            override fun onSuccess(response: BaseResponse<T>) {
                if (response.data == null) {
                    val exception = if (response.message != null) {
                        NetworkException(response.message)
                    } else {
                        NetworkException("Unknown error")
                    }
                    observer.onError(exception)
                } else {
                    observer.onSuccess(response.data)
                }
            }

        }
    }

}