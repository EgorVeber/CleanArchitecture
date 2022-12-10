package ru.gb.veber.paadlesson1.model.datasources

import io.reactivex.Observable
import ru.gb.veber.paadlesson1.model.datasources.network.DataModel

class DataSourceLocal(
    private val remoteProvider: RoomDataBaseImplementation =
        RoomDataBaseImplementation(),
) :
    DataSource<List<DataModel>> {
    override fun getData(word: String): Observable<List<DataModel>> =
        remoteProvider.getData(word)
}