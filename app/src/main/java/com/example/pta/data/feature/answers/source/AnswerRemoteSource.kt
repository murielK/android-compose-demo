package com.example.pta.data.feature.answers.source

import com.example.pta.data.feature.answers.model.Answer
import kotlinx.coroutines.flow.Flow

/**
 * Created by muriel on 25.05.2023..
 */
interface AnswerRemoteSource {

    fun getAnswersFor(id: Long): Flow<List<Answer>>
}