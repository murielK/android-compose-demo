package com.example.pta.domain.core

/**
 * Created by muriel on 26.05.2023..
 */
interface Mappers {

    interface ToUiModel<NetworkModel, UiModel> {
        fun toUiModel(networkModel: NetworkModel): UiModel
    }
}