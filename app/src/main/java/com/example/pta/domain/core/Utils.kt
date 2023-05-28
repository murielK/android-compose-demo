package com.example.pta.domain.core

import kotlinx.coroutines.delay
import kotlin.time.DurationUnit
import kotlin.time.toDuration

/**
 * Created by muriel on 26.05.2023..
 */

suspend fun ptaDefaultDelay() {
    delay(1.toDuration(DurationUnit.SECONDS))
}

