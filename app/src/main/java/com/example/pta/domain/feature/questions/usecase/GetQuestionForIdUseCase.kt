package com.example.pta.domain.feature.questions.usecase

import com.example.pta.domain.feature.questions.model.QuestionUi
import kotlinx.coroutines.flow.Flow

/**
 * Created by muriel on 26.05.2023..
 */
interface GetQuestionForIdUseCase {

    operator fun invoke(id: Long): Flow<QuestionUi>
}