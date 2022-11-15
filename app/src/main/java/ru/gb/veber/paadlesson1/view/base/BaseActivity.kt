package ru.gb.veber.paadlesson1.view.base

import androidx.appcompat.app.AppCompatActivity
import ru.gb.veber.paadlesson1.model.AppState
import ru.gb.veber.paadlesson1.viewmodel.BaseViewModel

abstract class BaseActivity<T : AppState> : AppCompatActivity() {
    abstract val model: BaseViewModel<T>
    abstract fun renderData(appState: T)
}
