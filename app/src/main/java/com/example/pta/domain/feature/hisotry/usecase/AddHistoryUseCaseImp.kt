package com.example.pta.domain.feature.hisotry.usecase

import com.example.pta.data.feature.hisotry.model.History
import com.example.pta.domain.feature.hisotry.repository.HistoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by muriel on 26.05.2023..
 */
class AddHistoryUseCaseImp @Inject constructor(private val historyRepository: HistoryRepository) :
    AddHistoryUseCase {

    override fun invoke(history: History): Flow<Unit> {
        return historyRepository.addHistory(history)
    }
}