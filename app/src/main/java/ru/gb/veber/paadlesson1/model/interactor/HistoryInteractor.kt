package ru.gb.veber.paadlesson1.model.interactor

import ru.gb.veber.paadlesson1.model.DataModel
import ru.gb.veber.paadlesson1.model.data.AppState
import ru.gb.veber.paadlesson1.model.reposotory.Repository
import ru.gb.veber.paadlesson1.model.reposotory.RepositoryLocal

class HistoryInteractor(
    private val repositoryRemote: Repository<List<DataModel>>,
    private val repositoryLocal: RepositoryLocal<List<DataModel>>,
) : Interactor<AppState> {
    override suspend fun getData(word: String, fromRemoteSource: Boolean):
            AppState {
        return AppState.Success(
            if (fromRemoteSource) {
                repositoryRemote
            } else {
                repositoryLocal
            }.getData(word)
        )
    }
}