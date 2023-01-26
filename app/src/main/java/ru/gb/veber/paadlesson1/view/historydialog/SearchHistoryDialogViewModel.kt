package ru.gb.veber.paadlesson1.view.historydialog

import androidx.lifecycle.LiveData
import kotlinx.coroutines.launch
import ru.gb.veber.model.data.AppState
import ru.gb.veber.paadlesson1.model.interactor.SearchHistoryDialogInteractor
import ru.gb.veber.paadlesson1.view.base.BaseViewModel

class SearchHistoryDialogViewModel(private val interactor: SearchHistoryDialogInteractor) :
    BaseViewModel<AppState>() {

    private val liveDataForViewToObserve: LiveData<AppState> = _mutableLiveData

    fun subscribe(): LiveData<AppState> {
        return liveDataForViewToObserve
    }

    override fun getData(word: String, isOnline: Boolean) {
        _mutableLiveData.value = AppState.Loading(null)
        cancelJob()
        viewModelCoroutineScope.launch { startInteractor(word, isOnline) }
    }


    private suspend fun startInteractor(word: String, isOnline: Boolean) {
        _mutableLiveData.postValue(interactor.getDataByWord(word))
    }

    override fun handleError(error: Throwable) {
        _mutableLiveData.postValue(AppState.Error(error))
    }

    override fun onCleared() {
        _mutableLiveData.value = AppState.Success(null)
        super.onCleared()
    }
}
