package com.example.pta.ui.feature.home.game.event

/**
 * Created by muriel on 26.05.2023..
 */
sealed class GameScreenEvent {

    data class OnQuestionsIds(val questionsIds: List<Long>) : GameScreenEvent()
}
