package com.example.pta.domain.feature.hisotry.usecase

import com.example.pta.domain.feature.hisotry.model.HistoryUi
import kotlinx.coroutines.flow.Flow

/**
 * Created by muriel on 26.05.2023..
 */
interface GetHistoriesUseCase {

    operator fun invoke(): Flow<List<HistoryUi>>
}