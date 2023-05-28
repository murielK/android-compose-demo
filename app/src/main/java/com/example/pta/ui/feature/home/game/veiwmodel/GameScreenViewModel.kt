package com.example.pta.ui.feature.home.game.veiwmodel

import androidx.collection.ArrayMap
import com.example.pta.domain.core.EventBus
import com.example.pta.domain.feature.answers.usecase.GetAnswersUseCase
import com.example.pta.domain.feature.questions.model.QuestionUi
import com.example.pta.domain.feature.questions.usecase.GetQuestionForIdUseCase
import com.example.pta.ui.core.base.BaseViewModel
import com.example.pta.ui.core.di.BottomNavigator
import com.example.pta.ui.core.isSafeActive
import com.example.pta.ui.core.navigation.Navigation
import com.example.pta.ui.feature.home.game.event.GameScreenEvent
import com.example.pta.ui.feature.home.game.state.GameState
import com.example.pta.ui.feature.home.result.destination.ResultScreenNavigationDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import javax.inject.Inject

/**
 * Created by muriel on 26.05.2023..
 */
@HiltViewModel
class GameScreenViewModel @Inject constructor(
    @BottomNavigator private val navigator: EventBus<Navigation>,
    private val questionForIdUseCase: GetQuestionForIdUseCase,
    private val getAnswerFoIdUseCase: GetAnswersUseCase,
) : BaseViewModel() {

    private val answeredMap = ArrayMap<QuestionUi, Int>()
    private var pointer = -1
    private val _gameState = MutableStateFlow<GameState>(GameState.Empty)
    val gameState: StateFlow<GameState> = _gameState
    private val questionsIds = mutableListOf<Long>()

    private var gameJob: Job? = null

    fun onEvent(event: GameScreenEvent) {
        if (event is GameScreenEvent.OnQuestionsIds) {
            questionsIds.addAll(event.questionsIds)
        }
        getGame()
    }

    fun onAnswer(index: Int) {
        if (isGameAvailable()) {
            val questionState = gameState.value as GameState.Question
            answeredMap[questionState.questionUi] = index
            updateState(currentQuestionState().copy(selectedIndex = index, canGoNext = true))
        }
    }

    fun onGoBackClicked() {
        if (canGoBack()) {
            getGame(false)
        }
    }

    fun onNextClicked() {
        if (canGoNext()) {
            getGame()
        } else if (isCanGoToFinish()) {
            navigator.postEvent(Navigation.Navigate(ResultScreenNavigationDestination.route()) {
                popUpTo(ResultScreenNavigationDestination.route())
            })
        }
    }

    private fun getGame(next: Boolean = true) {
        if (gameJob.isSafeActive().not()) {
            gameJob = launch {
                flowOf(
                    if (next) {
                        ++pointer
                    } else {
                        --pointer
                    }
                ).flatMapConcat { currentPointer ->
                    questionForIdUseCase(questionsIds[currentPointer]).flatMapConcat { q ->
                        getAnswerFoIdUseCase(questionsIds[currentPointer]).map { answers ->
                            val selectedIndex = answeredMap[q] ?: -1
                            GameState.Question(
                                questionUi = q,
                                answers,
                                canGoBack = currentPointer > 0,
                                selectedIndex = selectedIndex,
                                canGoNext = selectedIndex > -1
                            )
                        }

                    }
                }.onStart {
                    showLoadingState()
                }.onCompletion {
                    showIdleState()
                }.catch {
                    if (next) {
                        pointer--
                    } else {
                        pointer++
                    }
                    throw it
                }.collect {
                    updateState(it)
                }
            }
        }
    }

    private fun canGoNext(): Boolean {
        return gameJob.isSafeActive()
            .not() && isGameAvailable() && pointer < currentQuestionState().answers.size - 1
    }

    private fun canGoBack(): Boolean {
        return gameJob.isSafeActive().not() && isGameAvailable() && pointer > 0
    }

    private fun isCanGoToFinish(): Boolean {
        return gameJob.isSafeActive()
            .not() && isGameAvailable() && pointer == currentQuestionState().answers.size - 1
    }

    private fun isGameAvailable(): Boolean {
        return _gameState.value is GameState.Question
    }

    private fun updateState(gameState: GameState) {
        _gameState.update {
            gameState
        }
    }

    private fun currentQuestionState(): GameState.Question {
        return (_gameState.value as GameState.Question)
    }

}