package com.example.pta.domain.feature.answers.usecase

import com.example.pta.domain.feature.answers.model.AnswerUi
import com.example.pta.domain.feature.answers.repository.AnswerRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by muriel on 26.05.2023..
 */
class GetAnswerUseCaseImp @Inject constructor(private val answerRepository: AnswerRepository) :
    GetAnswersUseCase {

    override fun invoke(id: Long): Flow<List<AnswerUi>> {
        return answerRepository.getAnswersFor(id)
    }
}