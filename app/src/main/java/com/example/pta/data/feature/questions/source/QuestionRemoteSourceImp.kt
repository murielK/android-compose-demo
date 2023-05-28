package com.example.pta.data.feature.questions.source

import com.example.pta.data.feature.questions.model.Question
import com.example.pta.domain.core.FakeBackendException
import com.example.pta.domain.core.ptaDefaultDelay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by muriel on 26.05.2023..
 */
class QuestionRemoteSourceImp @Inject constructor() : QuestionRemoteSource {

    override fun getQuestionFor(id: Long): Flow<Question> = flow {
        //fake backend
        ptaDefaultDelay()
        when (id) {
            0L -> emit(
                Question(
                    0,
                    "You’re really busy at work and a colleague is telling you their life story and personal woes. You:"
                )
            )

            1L -> emit(
                Question(
                    0,
                    "You’ve been sitting in the doctor’s waiting room for more than 25 minutes. You:"
                )
            )

            2L -> emit(
                Question(
                    0,
                    "You’re having an animated discussion with a colleague regarding a project that you’re in charge of. You:"
                )
            )

            3L -> emit(Question(0, "You are taking part in a guided tour of a museum. You:"))
            else -> throw FakeBackendException()
        }
    }
}