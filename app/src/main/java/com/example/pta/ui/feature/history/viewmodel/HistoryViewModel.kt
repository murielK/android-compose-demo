package com.example.pta.ui.feature.history.viewmodel

import com.example.pta.domain.feature.hisotry.model.HistoryUi
import com.example.pta.domain.feature.hisotry.usecase.GetHistoriesUseCase
import com.example.pta.ui.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import javax.inject.Inject

/**
 * Created by muriel on 26.05.2023..
 */
@HiltViewModel
class HistoryViewModel @Inject constructor(private val historiesUseCase: GetHistoriesUseCase) :
    BaseViewModel() {

    private val _historiesState: MutableStateFlow<List<HistoryUi>> = MutableStateFlow(emptyList())
    val historiesState: StateFlow<List<HistoryUi>> = _historiesState

    init {
        fetchHistories()
    }

    private fun fetchHistories() {
        launch {
            historiesUseCase()
                .onStart {
                    showLoadingState()
                }
                .onCompletion {
                    showIdleState()
                }
                .collect { items ->
                    _historiesState.update { items }
                }
        }
    }

}