package com.example.pta.ui.feature.home.landing.veiwmodel

import com.example.pta.data.feature.hisotry.model.History
import com.example.pta.domain.core.EventBus
import com.example.pta.domain.feature.hisotry.usecase.AddHistoryUseCase
import com.example.pta.domain.feature.questions.usecase.GetAvailableQuestionsIdsUseCase
import com.example.pta.ui.core.base.BaseViewModel
import com.example.pta.ui.core.di.BottomNavigator
import com.example.pta.ui.core.isSafeActive
import com.example.pta.ui.core.navigation.Navigation
import com.example.pta.ui.feature.home.game.destination.GameScreenNavigationDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

/**
 * Created by muriel on 26.05.2023..
 */
@HiltViewModel
class LandingScreenViewModel @Inject constructor(
    @BottomNavigator private val navigator: EventBus<Navigation>,
    private val getAvailableQuestionsIdsUseCase: GetAvailableQuestionsIdsUseCase,
    private val addHistoryUseCase: AddHistoryUseCase,
) : BaseViewModel() {

    private var startJob: Job? = null

    fun onStartClicked() {
        if (startJob.isSafeActive().not()) {
            startJob = launch {
                getAvailableQuestionsIdsUseCase()
                    .onStart {
                        showLoadingState()
                        addHistoryUseCase(
                            History(
                                System.currentTimeMillis(),
                                System.currentTimeMillis()
                            )
                        ).collect()
                    }
                    .onCompletion {
                        showIdleState()
                    }
                    .collect {
                        navigateToGame(it)
                    }
            }
        }
    }

    private fun navigateToGame(questionsIds: List<Long>) {
        navigator.postEvent(Navigation.Navigate(GameScreenNavigationDestination.route(questionsIds)) {
            popUpTo(GameScreenNavigationDestination.route()) {
                inclusive = true
            }
        })
    }

}