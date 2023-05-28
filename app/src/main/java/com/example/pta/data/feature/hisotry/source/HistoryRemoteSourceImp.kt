package com.example.pta.data.feature.hisotry.source

import com.example.pta.data.feature.hisotry.model.History
import com.example.pta.domain.core.ptaDefaultDelay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by muriel on 26.05.2023..
 */
class HistoryRemoteSourceImp @Inject constructor() : HistoryRemoteSource {

    private val histories = mutableListOf<History>()

    override fun getHistories(): Flow<List<History>> = flow {
        //fake backend
        ptaDefaultDelay()
        emit(histories.toList())
    }

    override fun addHistory(history: History): Flow<Unit> = flow {
        ptaDefaultDelay()
        histories.add(history)
    }
}