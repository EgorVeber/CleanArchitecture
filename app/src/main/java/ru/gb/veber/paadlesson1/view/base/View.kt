package ru.gb.veber.paadlesson1.view.base

import ru.gb.veber.paadlesson1.model.data.AppState

interface View {
    fun renderData(appState: AppState)
}