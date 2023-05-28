package com.example.pta.data.feature.hisotry.source

import com.example.pta.data.feature.hisotry.model.History
import kotlinx.coroutines.flow.Flow

/**
 * Created by muriel on 26.05.2023..
 */
interface HistoryRemoteSource {

    fun getHistories(): Flow<List<History>>

    fun addHistory(history: History): Flow<Unit>
}