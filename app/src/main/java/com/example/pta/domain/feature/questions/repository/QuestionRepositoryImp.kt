package com.example.pta.domain.feature.questions.repository

import com.example.pta.data.feature.questions.source.QuestionRemoteSource
import com.example.pta.domain.feature.questions.mapper.QuestionUiMapper
import com.example.pta.domain.feature.questions.model.QuestionUi
import com.example.pta.domain.feature.questions.service.QuestionService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created by muriel on 26.05.2023..
 */
class QuestionRepositoryImp @Inject constructor(
    private val questionRemoteSource: QuestionRemoteSource,
    private val questionService: QuestionService,
    private val questionUiMapper: QuestionUiMapper
) : QuestionRepository {

    override fun getQuestionFor(id: Long): Flow<QuestionUi> {
        return questionRemoteSource.getQuestionFor(id).map { questionUiMapper.toUiModel(it) }
    }

    override fun availableQuestionIds(): Flow<List<Long>> {
        return questionService.availableQuestionIds()
    }
}