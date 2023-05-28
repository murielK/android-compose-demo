package com.example.pta.domain.feature.hisotry.repository

import com.example.pta.data.feature.hisotry.model.History
import com.example.pta.data.feature.hisotry.source.HistoryRemoteSource
import com.example.pta.domain.core.Mappers
import com.example.pta.domain.feature.hisotry.model.HistoryUi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created by muriel on 26.05.2023..
 */
class HistoryRepositoryImp @Inject constructor(
    private val historyRemoteSource: HistoryRemoteSource,
    private val historyUiMapper: Mappers.ToUiModel<History, HistoryUi>
) : HistoryRepository {

    override fun getHistories(): Flow<List<HistoryUi>> {
        return historyRemoteSource.getHistories()
            .map { listItems -> listItems.map { item -> historyUiMapper.toUiModel(item) } }
    }

    override fun addHistory(history: History): Flow<Unit> {
        return historyRemoteSource.addHistory(history)
    }
}