package ru.gb.veber.paadlesson1.model

import io.reactivex.Observable
import ru.gb.veber.paadlesson1.model.datasources.network.DataModel
import ru.gb.veber.paadlesson1.model.repository.Repository

class MainInteractor(
    val remoteRepo: Repository<List<DataModel>>,
    val localRepo: Repository<List<DataModel>>,
) : Interactor<AppState> {
    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        return AppState.Success(
            if (fromRemoteSource) {
                remoteRepo
            } else {
                localRepo
            }.getData(word)
        )
    }
}