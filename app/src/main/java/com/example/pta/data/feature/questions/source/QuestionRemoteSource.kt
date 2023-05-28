package com.example.pta.data.feature.questions.source

import com.example.pta.data.feature.questions.model.Question
import kotlinx.coroutines.flow.Flow

/**
 * Created by muriel on 26.05.2023..
 */
interface QuestionRemoteSource {

    fun getQuestionFor(id: Long): Flow<Question>
}
