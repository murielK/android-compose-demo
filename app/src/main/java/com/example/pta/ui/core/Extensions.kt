package com.example.pta.ui.core

import kotlinx.coroutines.Job

/**
 * Created by muriel on 26.05.2023..
 */

fun Job?.isSafeActive(): Boolean {
    return this != null && this.isActive
}