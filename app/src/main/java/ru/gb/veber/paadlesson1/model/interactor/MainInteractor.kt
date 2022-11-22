package ru.gb.veber.paadlesson1.model.interactor

import ru.gb.veber.paadlesson1.model.DataModel
import ru.gb.veber.paadlesson1.model.data.AppState
import ru.gb.veber.paadlesson1.model.reposotory.Repository

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