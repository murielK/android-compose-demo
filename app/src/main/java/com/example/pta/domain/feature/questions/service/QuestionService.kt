package com.example.pta.domain.feature.questions.service

import kotlinx.coroutines.flow.Flow

/**
 * Created by muriel on 26.05.2023..
 */
interface QuestionService {

    fun availableQuestionIds(): Flow<List<Long>>
}