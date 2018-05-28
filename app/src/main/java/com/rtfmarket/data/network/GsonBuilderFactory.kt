package com.rtfmarket.data.network

import com.google.gson.GsonBuilder
import javax.inject.Inject

class GsonBuilderFactory
@Inject constructor() {

    fun newInstance(): GsonBuilder {
        return GsonBuilder()
    }
}
