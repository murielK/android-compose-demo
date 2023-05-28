package com.example.pta.ui.feature.home.game.state

import com.example.pta.domain.feature.answers.model.AnswerUi
import com.example.pta.domain.feature.questions.model.QuestionUi
import com.example.pta.ui.core.State

/**
 * Created by muriel on 26.05.2023..
 */
sealed class GameState : State {

    object Empty : GameState()

    data class Question(
        val questionUi: QuestionUi,
        val answers: List<AnswerUi>,
        val selectedIndex: Int = -1,
        val canGoBack: Boolean,
        val canGoNext: Boolean
    ) : GameState()
}