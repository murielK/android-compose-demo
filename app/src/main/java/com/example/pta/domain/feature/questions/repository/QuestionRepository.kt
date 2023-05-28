package com.example.pta.domain.feature.questions.repository

import com.example.pta.domain.feature.questions.model.QuestionUi
import kotlinx.coroutines.flow.Flow

/**
 * Created by muriel on 26.05.2023..
 */
interface QuestionRepository {

    fun getQuestionFor(id: Long): Flow<QuestionUi>

    fun availableQuestionIds(): Flow<List<Long>>
}