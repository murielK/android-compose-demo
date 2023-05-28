package com.example.pta.domain.feature.questions.service

import com.example.pta.domain.core.ptaDefaultDelay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by muriel on 26.05.2023..
 */
class QuestionServiceImp @Inject constructor() : QuestionService {

    override fun availableQuestionIds(): Flow<List<Long>> = flow {
        ptaDefaultDelay()
        emit(listOf(0L, 1L, 2L, 3L))
    }
}