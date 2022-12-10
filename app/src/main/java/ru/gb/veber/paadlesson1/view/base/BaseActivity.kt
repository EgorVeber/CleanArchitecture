package ru.gb.veber.paadlesson1.view.base

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import ru.gb.veber.paadlesson1.model.AppState
import ru.gb.veber.paadlesson1.presenter.Presenter

abstract class BaseActivity<T : AppState> : AppCompatActivity(), View {

    protected lateinit var presenter: Presenter<T, View>

    protected abstract fun createPresenter(): Presenter<T, View>

    abstract override fun renderData(appState: AppState)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = createPresenter()
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView(this)
    }
}