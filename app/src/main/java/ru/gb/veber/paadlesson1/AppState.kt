package ru.gb.veber.paadlesson1

sealed class AppState {
    data class Success(val data: List<Any>?) : AppState()
    data class Error(val error: Throwable) : AppState()
    data class Loading(val progress: Int?) : AppState()
}