package ru.gb.veber.paadlesson1.model.reposotory

import ru.gb.veber.model.data.AppState
import ru.gb.veber.model.data.DataModel

class RepositoryImplementationLocal(private val dataSource: DataSourceLocal<List<DataModel>>) :
    RepositoryLocal<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.getData(word)
    }

    override suspend fun saveToDB(appState: AppState) {
        dataSource.saveToDB(appState)
    }

    override suspend fun getDataByWord(word: String): DataModel {
        return dataSource.getDataByWord(word)
    }
}
