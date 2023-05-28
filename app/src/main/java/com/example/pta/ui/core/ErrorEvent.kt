package com.example.pta.ui.core

/**
 * Created by muriel on 26.05.2023..
 */
sealed class ErrorEvent {

    object UnknownError : ErrorEvent()

    object FakeBackendError : ErrorEvent()

    class Error(val message: String) : ErrorEvent()
}
