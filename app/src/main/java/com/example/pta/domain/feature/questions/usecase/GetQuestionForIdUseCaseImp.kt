package com.example.pta.domain.feature.questions.usecase

import com.example.pta.domain.feature.questions.model.QuestionUi
import com.example.pta.domain.feature.questions.repository.QuestionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by muriel on 26.05.2023..
 */
class GetQuestionForIdUseCaseImp @Inject constructor(private val questionRepository: QuestionRepository) :
    GetQuestionForIdUseCase {

    override fun invoke(id: Long): Flow<QuestionUi> {
        return questionRepository.getQuestionFor(id)
    }
}