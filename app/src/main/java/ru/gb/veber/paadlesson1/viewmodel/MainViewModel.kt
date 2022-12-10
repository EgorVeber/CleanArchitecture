package ru.gb.veber.paadlesson1.viewmodel

import androidx.lifecycle.LiveData
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import ru.gb.veber.paadlesson1.model.AppState
import ru.gb.veber.paadlesson1.model.MainInteractor
import ru.gb.veber.paadlesson1.model.datasources.DataSourceLocal
import ru.gb.veber.paadlesson1.model.datasources.DataSourceRemote
import ru.gb.veber.paadlesson1.model.datasources.network.DataModel
import ru.gb.veber.paadlesson1.model.datasources.network.Meanings
import ru.gb.veber.paadlesson1.model.repository.RepositoryImplementation
import ru.gb.veber.paadlesson1.utils.parseSearchResults
import javax.inject.Inject

class MainViewModel @Inject constructor(private val interactor: MainInteractor) :
    BaseViewModel<AppState>() {

    private var appState: AppState? = null

    fun subscribe(): LiveData<AppState> {
        return liveDataForViewToObserve
    }

    override fun getData(word: String, isOnline: Boolean) {
        compositeDisposable.add(
            interactor.getData(word, isOnline)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .doOnSubscribe(doOnSubscribe())
                .subscribeWith(getObserver())
        )
    }

    private fun doOnSubscribe(): (Disposable) -> Unit =
        { liveDataForViewToObserve.value = AppState.Loading(null) }

    private fun getObserver(): DisposableObserver<AppState> {
        return object : DisposableObserver<AppState>() {

            override fun onNext(state: AppState) {
                appState = parseSearchResults(state)
                liveDataForViewToObserve.value = appState
            }

            override fun onError(e: Throwable) {
                liveDataForViewToObserve.value = AppState.Error(e)
            }

            override fun onComplete() {
            }
        }
    }
}
