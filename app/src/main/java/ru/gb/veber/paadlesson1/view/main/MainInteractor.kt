package ru.gb.veber.paadlesson1.view.main

import io.reactivex.Observable
import ru.gb.veber.paadlesson1.model.data.AppState
import ru.gb.veber.paadlesson1.model.data.DataModel
import ru.gb.veber.paadlesson1.model.repository.Repository
import ru.gb.veber.paadlesson1.presenter.Interactor

class MainInteractor(
    private val remoteRepo: Repository<List<DataModel>>,
    private val localRepo: Repository<List<DataModel>>,
) : Interactor<AppState> {
    override fun getData(word: String, fromRemoteSources: Boolean): Observable<AppState> {
        return if (fromRemoteSources) {
            remoteRepo.getData(word).map { AppState.Success(it) }
        } else {
            localRepo.getData(word).map { AppState.Success(it) }
        }
    }
}