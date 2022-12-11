package ru.gb.veber.paadlesson1.model.datasource

import ru.gb.veber.paadlesson1.core.utils.convertDataModelSuccessToEntity
import ru.gb.veber.paadlesson1.core.utils.mapHistoryEntityToSearchResult
import ru.gb.veber.paadlesson1.core.utils.mapHistoryToData
import ru.gb.veber.paadlesson1.model.data.DataModel
import ru.gb.veber.paadlesson1.model.data.AppState
import ru.gb.veber.paadlesson1.model.database.HistoryDao
import ru.gb.veber.paadlesson1.model.reposotory.DataSourceLocal

class RoomDataBaseImplementation(private val historyDao: HistoryDao) :
    DataSourceLocal<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return mapHistoryEntityToSearchResult(historyDao.all())
    }

    override suspend fun saveToDB(appState: AppState) {
        convertDataModelSuccessToEntity(appState)?.let {
            historyDao.insert(it)
        }
    }

    override suspend fun getDataByWord(word: String): DataModel {
        return mapHistoryToData(historyDao.getDataByWord(word))
    }
}
