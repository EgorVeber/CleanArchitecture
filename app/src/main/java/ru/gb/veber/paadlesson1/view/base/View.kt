package ru.gb.veber.paadlesson1.view.base

import ru.gb.veber.paadlesson1.model.AppState

interface View {
    fun renderData(appState: AppState)
}