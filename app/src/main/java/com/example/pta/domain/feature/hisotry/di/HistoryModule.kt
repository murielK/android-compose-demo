package com.example.pta.domain.feature.hisotry.di

import com.example.pta.data.feature.hisotry.model.History
import com.example.pta.data.feature.hisotry.source.HistoryRemoteSource
import com.example.pta.data.feature.hisotry.source.HistoryRemoteSourceImp
import com.example.pta.domain.core.Mappers
import com.example.pta.domain.feature.hisotry.mapper.HistoryUiMapper
import com.example.pta.domain.feature.hisotry.model.HistoryUi
import com.example.pta.domain.feature.hisotry.repository.HistoryRepository
import com.example.pta.domain.feature.hisotry.repository.HistoryRepositoryImp
import com.example.pta.domain.feature.hisotry.usecase.AddHistoryUseCase
import com.example.pta.domain.feature.hisotry.usecase.AddHistoryUseCaseImp
import com.example.pta.domain.feature.hisotry.usecase.GetHistoriesUseCase
import com.example.pta.domain.feature.hisotry.usecase.GetHistoriesUseCaseImp
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Singleton

/**
 * Created by muriel on 26.05.2023..
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class HistoryModule {

    @Binds
    @Singleton
    abstract fun provideHistoryUiMapper(historyUiMapper: HistoryUiMapper): Mappers.ToUiModel<History, HistoryUi>

    @Binds
    @Singleton
    abstract fun provideHistoryRemoteSource(historyRemoteSourceImp: HistoryRemoteSourceImp): HistoryRemoteSource

    @Binds
    @Singleton
    abstract fun provideHistoryRepository(historyRepository: HistoryRepositoryImp): HistoryRepository

    @Binds
    @Singleton
    abstract fun provideAddHistoryUserCase(addHistoryUseCaseImp: AddHistoryUseCaseImp): AddHistoryUseCase

    @Binds
    @Singleton
    abstract fun provideGetHistoriesUseCase(getHistoriesUseCase: GetHistoriesUseCaseImp): GetHistoriesUseCase

    companion object {

        @Provides
        @Singleton
        fun provideSimpleDateFormat(): DateFormat {
            return SimpleDateFormat("dd MMMM yyyy, HH:mm", Locale.ENGLISH)
        }
    }

}