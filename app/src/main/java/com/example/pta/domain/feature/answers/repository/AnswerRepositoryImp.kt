package com.example.pta.domain.feature.answers.repository

import com.example.pta.data.feature.answers.model.Answer
import com.example.pta.data.feature.answers.source.AnswerRemoteSource
import com.example.pta.domain.core.Mappers
import com.example.pta.domain.feature.answers.model.AnswerUi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created by muriel on 26.05.2023..
 */
class AnswerRepositoryImp @Inject constructor(
    private val answerRemoteSource: AnswerRemoteSource,
    private val answerUiMapper: Mappers.ToUiModel<Answer, AnswerUi>
) : AnswerRepository {

    override fun getAnswersFor(id: Long): Flow<List<AnswerUi>> {
        return answerRemoteSource.getAnswersFor(id)
            .map { listItems -> listItems.map { item -> answerUiMapper.toUiModel(item) } }
    }

}