package com.example.pta.domain.feature.answers.di

import com.example.pta.data.feature.answers.model.Answer
import com.example.pta.data.feature.answers.source.AnswerRemoteSource
import com.example.pta.data.feature.answers.source.AnswerRemoteSourceImp
import com.example.pta.domain.core.Mappers
import com.example.pta.domain.feature.answers.mapper.AnswerUiMapper
import com.example.pta.domain.feature.answers.model.AnswerUi
import com.example.pta.domain.feature.answers.repository.AnswerRepository
import com.example.pta.domain.feature.answers.repository.AnswerRepositoryImp
import com.example.pta.domain.feature.answers.usecase.GetAnswerUseCaseImp
import com.example.pta.domain.feature.answers.usecase.GetAnswersUseCase
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
abstract class AnswerModule {

    @Binds
    @Singleton
    abstract fun provideAnswerUiMapper(answerUiMapper: AnswerUiMapper): Mappers.ToUiModel<Answer, AnswerUi>

    @Binds
    @Singleton
    abstract fun provideAnswerRemoteSource(answerRemoteSource: AnswerRemoteSourceImp): AnswerRemoteSource

    @Binds
    @Singleton
    abstract fun provideAnswerRepository(answerRemoteSource: AnswerRepositoryImp): AnswerRepository

    @Binds
    @Singleton
    abstract fun provideGetAnswerUseCase(getAnswersUseCase: GetAnswerUseCaseImp): GetAnswersUseCase

}