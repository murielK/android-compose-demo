package com.example.pta.ui.core.di

import com.example.pta.domain.core.EventBus
import com.example.pta.ui.core.navigation.Navigation
import com.example.pta.ui.core.navigation.Navigator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

/**
 * Created by muriel on 26.05.2023..
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class CoreModule {

    @Binds
    @MainNavigator
    @Singleton
    abstract fun provideNavigationEvent(navigator: Navigator): EventBus<Navigation>

    @Binds
    @BottomNavigator
    @Singleton
    abstract fun provideBottomNavigationEvent(navigator: Navigator): EventBus<Navigation>
}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class BottomNavigator

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class MainNavigator