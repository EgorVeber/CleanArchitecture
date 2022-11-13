package ru.gb.veber.paadlesson1.presenter

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import ru.gb.veber.paadlesson1.AppState
import ru.gb.veber.paadlesson1.model.DataSourceLocal
import ru.gb.veber.paadlesson1.model.DataSourceRemote
import ru.gb.veber.paadlesson1.model.RepositoryImplementation
import ru.gb.veber.paadlesson1.myinterface.Presenter
import ru.gb.veber.paadlesson1.myinterface.View

class MainPresenterImpl<T : AppState, V : View>(
    private val interact: MainInteractor = MainInteractor(
        RepositoryImplementation(DataSourceRemote()),
        RepositoryImplementation(DataSourceLocal())),
    protected val compositeDisposable: CompositeDisposable = CompositeDisposable(),
    protected val schedulerProvider: SchedulerProvider = SchedulerProvider(),
) : Presenter<T, V> {

    private var currentView: V? = null

    override fun attachView(view: V) {
        if (view != currentView) {
            currentView = view
        }
    }

    override fun detachView(view: V) {
        compositeDisposable.clear()
        if (view == currentView) {
            currentView = null
        }
    }

    override fun getData(word: String, isOnline: Boolean) {
        compositeDisposable.add(
            interact.getData(word, isOnline)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .doOnSubscribe { currentView?.renderData(AppState.Loading(null)) }
                .subscribeWith(getObserver()))
    }

    private fun getObserver(): DisposableObserver<AppState> {
        return object : DisposableObserver<AppState>() {
            override fun onNext(appState: AppState) {
                currentView?.renderData(appState)
            }

            override fun onError(e: Throwable) {
                currentView?.renderData(AppState.Error(e))
            }

            override fun onComplete() {
            }
        }
    }
}