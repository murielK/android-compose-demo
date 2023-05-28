package com.example.pta.domain.feature.hisotry.mapper

import com.example.pta.data.feature.hisotry.model.History
import com.example.pta.domain.core.Mappers
import com.example.pta.domain.feature.hisotry.model.HistoryUi
import java.text.DateFormat
import javax.inject.Inject

/**
 * Created by muriel on 26.05.2023..
 */
class HistoryUiMapper @Inject constructor(private val dateFormat: DateFormat) :
    Mappers.ToUiModel<History, HistoryUi> {

    override fun toUiModel(networkModel: History): HistoryUi {
        return HistoryUi(networkModel.id, dateFormat.format(networkModel.timestamp))
    }
}