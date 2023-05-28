package com.example.pta.domain.core

import kotlinx.coroutines.flow.SharedFlow

/**
 * Created by muriel on 26.05.2023..
 */
interface EventBus<T> {

    val events: SharedFlow<T>

    fun postEvent(event: T): Boolean
}