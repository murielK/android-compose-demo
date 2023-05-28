package com.example.pta.data.feature.answers.source

import com.example.pta.data.feature.answers.model.Answer
import com.example.pta.domain.core.FakeBackendException
import com.example.pta.domain.core.ptaDefaultDelay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by muriel on 25.05.2023..
 */
class AnswerRemoteSourceImp @Inject constructor() : AnswerRemoteSource {

    override fun getAnswersFor(id: Long): Flow<List<Answer>> = flow {
        //fake backend
        ptaDefaultDelay()
        when (id) {
            0L -> emit(
                listOf(
                    Answer(1, "Don’t dare to interrupt them"),
                    Answer(
                        2,
                        "Think it’s more important to give them some of your time; work can wait"
                    ),
                    Answer(3, "Listen, but with only with half an ear"),
                    Answer(3, "Interrupt and explain that you are really busy at the moment")
                )
            )

            1L -> emit(
                listOf(
                    Answer(1, "Look at your watch every two minutes"),
                    Answer(2, "Bubble with inner anger, but keep quiet"),
                    Answer(
                        3,
                        "Explain to other equally impatient people in the room that the doctor is always running late"
                    ),
                    Answer(3, "Complain in a loud voice, while tapping your foot impatiently")
                )
            )

            2L -> emit(
                listOf(
                    Answer(1, "Don’t dare contradict them"),
                    Answer(2, "Think that they are obviously right"),
                    Answer(3, "Defend your own point of view, tooth and nail"),
                    Answer(3, "Continuously interrupt your colleague")
                )
            )

            3L -> emit(
                listOf(
                    Answer(
                        1,
                        "Are a bit too far towards the back so don’t really hear what the guide is saying"
                    ),
                    Answer(2, "Follow the group without question"),
                    Answer(3, "Make sure that everyone is able to hear properly"),
                    Answer(3, "Are right up the front, adding your own comments in a loud voice")
                )
            )

            else -> throw FakeBackendException()
        }
    }
}