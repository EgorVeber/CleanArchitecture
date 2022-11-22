package ru.gb.veber.paadlesson1.model.datasource

import ru.gb.veber.paadlesson1.model.DataModel

class RoomDataBaseImplementation : DataSource<List<DataModel>> {
    override suspend fun getData(word: String): List<DataModel> {
        TODO("not implemented")
    }
}