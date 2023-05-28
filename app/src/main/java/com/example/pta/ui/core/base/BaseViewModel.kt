package com.example.pta.ui.core.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pta.domain.core.FakeBackendException
import com.example.pta.ui.core.ErrorEvent
import com.example.pta.ui.core.LoadingState
import com.example.pta.ui.core.State
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * Created by muriel on 26.05.2023..
 */
abstract class BaseViewModel : ViewModel() {

    private val _loadingState: MutableStateFlow<State> = MutableStateFlow(LoadingState.Idle)
    val loadingState: StateFlow<State> = _loadingState

    private val _errorEvent: MutableSharedFlow<ErrorEvent> = MutableSharedFlow()
    val errorEvent: SharedFlow<ErrorEvent> = _errorEvent

    val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        showIdleState()
        if (throwable is FakeBackendException) {
            handleFakeBackendError()
            return@CoroutineExceptionHandler
        }

        throwable.message?.let {
            showError(it)
        }
    }

    protected inline fun launch(
        coroutineScope: CoroutineScope,
        crossinline block: suspend CoroutineScope.() -> Unit
    ): Job {
        return coroutineScope.launch(context = coroutineExceptionHandler) {
            block()
        }
    }

    protected inline fun launch(crossinline block: suspend CoroutineScope.() -> Unit): Job =
        launch(viewModelScope, block)

    protected fun showError(msg: String) {
        _errorEvent.tryEmit(ErrorEvent.Error(msg))
    }

    protected fun showLoadingState() {
        _loadingState.update { LoadingState.Loading }
    }

    protected fun showIdleState() {
        _loadingState.update {
            LoadingState.Idle
        }
    }

    private fun handleFakeBackendError() {
        showError("fake backend threw some error...sad :(")
    }
}