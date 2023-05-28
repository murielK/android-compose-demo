package com.example.pta.domain.feature.hisotry.usecase

import com.example.pta.data.feature.hisotry.model.History
import kotlinx.coroutines.flow.Flow

/**
 * Created by muriel on 26.05.2023..
 */
interface AddHistoryUseCase {

    operator fun invoke(history: History): Flow<Unit>
}