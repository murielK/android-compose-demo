package com.example.pta.domain.feature.answers.repository

import com.example.pta.domain.feature.answers.model.AnswerUi
import kotlinx.coroutines.flow.Flow

/**
 * Created by muriel on 26.05.2023..
 */
interface AnswerRepository {

    fun getAnswersFor(id: Long): Flow<List<AnswerUi>>
}