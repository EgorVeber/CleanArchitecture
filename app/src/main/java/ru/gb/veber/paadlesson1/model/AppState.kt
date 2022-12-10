package ru.gb.veber.paadlesson1.model

import ru.gb.veber.paadlesson1.model.datasources.network.DataModel

sealed class AppState {
    data class Success(val data: List<DataModel>?) : AppState()
    data class Error(val error: Throwable) : AppState()
    data class Loading(val progress: Int?) : AppState()
}