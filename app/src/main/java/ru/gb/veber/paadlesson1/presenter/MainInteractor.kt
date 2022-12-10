package ru.gb.veber.paadlesson1.presenter

import io.reactivex.Observable
import ru.gb.veber.paadlesson1.model.AppState
import ru.gb.veber.paadlesson1.model.datasources.network.DataModel
import ru.gb.veber.paadlesson1.model.repository.Repository

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