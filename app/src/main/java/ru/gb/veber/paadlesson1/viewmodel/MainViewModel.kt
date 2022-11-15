package ru.gb.veber.paadlesson1.viewmodel

import androidx.lifecycle.LiveData
import io.reactivex.observers.DisposableObserver
import ru.gb.veber.paadlesson1.model.AppState
import ru.gb.veber.paadlesson1.model.datasources.DataSourceLocal
import ru.gb.veber.paadlesson1.model.datasources.DataSourceRemote
import ru.gb.veber.paadlesson1.model.repository.RepositoryImplementation
import ru.gb.veber.paadlesson1.model.MainInteractor

class MainViewModel(
    private val interact: MainInteractor = MainInteractor(
        RepositoryImplementation(DataSourceRemote()),
        RepositoryImplementation(DataSourceLocal())),
) : BaseViewModel<AppState>() {

    private var appState: AppState? = null

    override fun getData(word: String, isOnline: Boolean): LiveData<AppState> {
        compositeDisposable.add(
            interact.getData(word, isOnline)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .doOnSubscribe {
                    liveDataForViewToObserve.value = AppState.Loading(null)
                }.subscribeWith(getObserver()))

        return super.getData(word, isOnline)
    }

    private fun getObserver(): DisposableObserver<AppState> {
        return object : DisposableObserver<AppState>() {
            override fun onNext(state: AppState) {
                appState = state
                liveDataForViewToObserve.value = state
            }

            override fun onError(e: Throwable) {
                liveDataForViewToObserve.value = AppState.Error(e)
            }

            override fun onComplete() {
            }
        }
    }
}