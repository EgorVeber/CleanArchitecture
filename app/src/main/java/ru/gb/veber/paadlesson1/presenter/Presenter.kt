package ru.gb.veber.paadlesson1.presenter

import ru.gb.veber.paadlesson1.model.data.AppState
import ru.gb.veber.paadlesson1.view.base.View
interface Presenter<T : AppState, V : View> {
    fun attachView(view: V)
    fun detachView(view: V)
    fun getData(word: String, isOnline: Boolean)
}