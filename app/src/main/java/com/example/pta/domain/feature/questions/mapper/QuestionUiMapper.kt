package com.example.pta.domain.feature.questions.mapper


import com.example.pta.data.feature.questions.model.Question
import com.example.pta.domain.core.Mappers
import com.example.pta.domain.feature.questions.model.QuestionUi
import javax.inject.Inject

/**
 * Created by muriel on 26.05.2023..
 */
class QuestionUiMapper @Inject constructor() : Mappers.ToUiModel<Question, QuestionUi> {

    override fun toUiModel(networkModel: Question): QuestionUi {
        return QuestionUi(networkModel.id, networkModel.question)
    }
}