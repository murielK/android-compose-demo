package com.example.pta.domain.feature.hisotry.repository

import com.example.pta.data.feature.hisotry.model.History
import com.example.pta.domain.feature.hisotry.model.HistoryUi
import kotlinx.coroutines.flow.Flow

/**
 * Created by muriel on 26.05.2023..
 */
interface HistoryRepository {

    fun getHistories(): Flow<List<HistoryUi>>

    fun addHistory(history: History): Flow<Unit>
}