package com.example.pta.domain.core

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

/**
 * Created by muriel on 26.05.2023..
 */
open class BaseEventBus<T> : EventBus<T> {

    private val _events: MutableSharedFlow<T> = MutableSharedFlow(
        replay = 0,
        extraBufferCapacity = 100,
        BufferOverflow.DROP_OLDEST,
    )

    override val events: SharedFlow<T>
        get() = _events

    override fun postEvent(event: T): Boolean {
        if (_events.subscriptionCount.value > 0) {
            return _events.tryEmit(event)
        }

        return false
    }
}