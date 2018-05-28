package com.rtfmarket.data.network.model

class BaseResponse<out T>(val data: T?,
                          val message: String?)