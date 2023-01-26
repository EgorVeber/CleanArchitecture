package ru.gb.veber.paadlesson1.model.interactor

import ru.gb.veber.paadlesson1.model.data.DataModel
import ru.gb.veber.paadlesson1.model.data.AppState
import ru.gb.veber.paadlesson1.model.reposotory.RepositoryLocal

class SearchHistoryDialogInteractor(
    private val repositoryLocal: RepositoryLocal<List<DataModel>>,
) : Interactor<AppState> {
    override suspend fun getData(word: String, fromRemoteSource: Boolean):
            AppState {
        return AppState.Success(
            repositoryLocal.getData(word)
        )
    }

    suspend fun getDataByWord(word: String): AppState {
        return AppState.SuccessKeyWord(
            repositoryLocal.getDataByWord(word)
        )
    }
}