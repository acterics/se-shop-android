package com.rtfmarket.common.navigation

import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router

class CiceroneHolder<T: Router>(private val parentRouter: Router,
                                private val childRouterInitializer: () -> T) {
    private val cicerones = hashMapOf<String, Cicerone<T>>()

    operator fun get(key: String): Cicerone<T> {
        return cicerones.getOrPut(key) { Cicerone.create(childRouterInitializer()) }
    }
}