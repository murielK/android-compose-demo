package com.example.pta.domain.feature.questions.usecase

import kotlinx.coroutines.flow.Flow

/**
 * Created by muriel on 26.05.2023..
 */
interface GetAvailableQuestionsIdsUseCase {

    operator fun invoke(): Flow<List<Long>>
}