package com.example.pta.domain.feature.hisotry.usecase

import com.example.pta.domain.feature.hisotry.model.HistoryUi
import com.example.pta.domain.feature.hisotry.repository.HistoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by muriel on 26.05.2023..
 */
class GetHistoriesUseCaseImp @Inject constructor(private val historyRepository: HistoryRepository) :
    GetHistoriesUseCase {

    override fun invoke(): Flow<List<HistoryUi>> {
        return historyRepository.getHistories()
    }

}