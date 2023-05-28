package com.example.pta.domain.feature.questions.di

import com.example.pta.data.feature.questions.model.Question
import com.example.pta.data.feature.questions.source.QuestionRemoteSource
import com.example.pta.data.feature.questions.source.QuestionRemoteSourceImp
import com.example.pta.domain.core.Mappers
import com.example.pta.domain.feature.questions.mapper.QuestionUiMapper
import com.example.pta.domain.feature.questions.model.QuestionUi
import com.example.pta.domain.feature.questions.repository.QuestionRepository
import com.example.pta.domain.feature.questions.repository.QuestionRepositoryImp
import com.example.pta.domain.feature.questions.service.QuestionService
import com.example.pta.domain.feature.questions.service.QuestionServiceImp
import com.example.pta.domain.feature.questions.usecase.GetAvailableQuestionsIdsUseCase
import com.example.pta.domain.feature.questions.usecase.GetAvailableQuestionsIdsUseCaseImp
import com.example.pta.domain.feature.questions.usecase.GetQuestionForIdUseCase
import com.example.pta.domain.feature.questions.usecase.GetQuestionForIdUseCaseImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by muriel on 26.05.2023..
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class QuestionModule {

    @Binds
    @Singleton
    abstract fun provideQuestionUiMapper(questionUiMapper: QuestionUiMapper): Mappers.ToUiModel<Question, QuestionUi>

    @Binds
    @Singleton
    abstract fun provideQuestionRemoteSource(questionRemoteSourceImp: QuestionRemoteSourceImp): QuestionRemoteSource

    @Binds
    @Singleton
    abstract fun provideQuestionService(questionServiceImp: QuestionServiceImp): QuestionService

    @Binds
    @Singleton
    abstract fun provideQuestionRepository(questionRepository: QuestionRepositoryImp): QuestionRepository

    @Binds
    @Singleton
    abstract fun provideGetQuestionForIdUseCase(getQuestionForIdUseCase: GetQuestionForIdUseCaseImp): GetQuestionForIdUseCase

    @Binds
    @Singleton
    abstract fun provideGetAvailableQuestionsUseCase(getAvailableQuestionsIdsImp: GetAvailableQuestionsIdsUseCaseImp): GetAvailableQuestionsIdsUseCase

}