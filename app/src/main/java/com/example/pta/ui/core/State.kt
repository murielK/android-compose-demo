package com.example.pta.ui.core

/**
 * Created by muriel on 26.05.2023..
 */
interface State

sealed class LoadingState : State {

    object Loading : LoadingState()
    object Idle : LoadingState()
}