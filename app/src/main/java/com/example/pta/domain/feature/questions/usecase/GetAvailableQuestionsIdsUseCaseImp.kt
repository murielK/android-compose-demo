package com.example.pta.domain.feature.questions.usecase

import com.example.pta.domain.feature.questions.repository.QuestionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by muriel on 26.05.2023..
 */
class GetAvailableQuestionsIdsUseCaseImp @Inject constructor(private val questionRepository: QuestionRepository) :
    GetAvailableQuestionsIdsUseCase {

    override fun invoke(): Flow<List<Long>> {
        return questionRepository.availableQuestionIds()
    }
}