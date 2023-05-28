package com.example.pta.domain.feature.answers.usecase

import com.example.pta.domain.feature.answers.model.AnswerUi
import kotlinx.coroutines.flow.Flow

/**
 * Created by muriel on 26.05.2023..
 */
interface GetAnswersUseCase {

    operator fun invoke(id: Long): Flow<List<AnswerUi>>
}