package com.example.pta.domain.feature.answers.mapper

import com.example.pta.data.feature.answers.model.Answer
import com.example.pta.domain.core.Mappers
import com.example.pta.domain.feature.answers.model.AnswerUi
import javax.inject.Inject

/**
 * Created by muriel on 26.05.2023..
 */
class AnswerUiMapper @Inject constructor() : Mappers.ToUiModel<Answer, AnswerUi> {

    override fun toUiModel(networkModel: Answer): AnswerUi {
        return AnswerUi(networkModel.id, networkModel.answer)
    }
}