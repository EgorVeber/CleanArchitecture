package ru.gb.veber.paadlesson1.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import ru.gb.veber.paadlesson1.model.AppState
import ru.gb.veber.paadlesson1.rx.SchedulerProvider

abstract class BaseViewModel<T : AppState>(
    protected val liveDataForViewToObserve: MutableLiveData<T> = MutableLiveData(),
    protected val compositeDisposable: CompositeDisposable = CompositeDisposable(),
    protected val schedulerProvider: SchedulerProvider = SchedulerProvider(),
) : ViewModel() {
    abstract fun getData(word: String, isOnline: Boolean)


   // open fun getData(word: String, isOnline: Boolean): LiveData<T> = liveDataForViewToObserve
    override fun onCleared() {
        compositeDisposable.clear()
    }

}