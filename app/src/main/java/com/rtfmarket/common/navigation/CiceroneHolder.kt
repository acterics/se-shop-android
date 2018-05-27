package com.rtfmarket.common.navigation

import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router

abstract class CiceroneHolder<T: Router>  {
    private val cicerones = hashMapOf<String, Cicerone<T>>()
    operator fun get(key: String): Cicerone<T> {
        return cicerones.getOrPut(key, { Cicerone.create(createNewRouter(key)) })
    }

    abstract fun createNewRouter(key: String): T
}