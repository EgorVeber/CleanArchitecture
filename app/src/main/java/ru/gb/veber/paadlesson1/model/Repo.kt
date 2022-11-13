package ru.gb.veber.paadlesson1.model

import io.reactivex.Observable
import ru.gb.veber.paadlesson1.myinterface.DataSource

// Для получения внешних данных мы будем использовать Retrofit
class DataSourceRemote(
    private val remoteProvider: RetrofitImplementation =
        RetrofitImplementation(),
) :
    DataSource<List<DataModel>> {
    override fun getData(word: String): Observable<List<DataModel>> =
        remoteProvider.getData(word)
}

// Для локальных данных используется Room
class DataSourceLocal(
    private val remoteProvider: RoomDataBaseImplementation =
        RoomDataBaseImplementation(),
) :
    DataSource<List<DataModel>> {
    override fun getData(word: String): Observable<List<DataModel>> =
        remoteProvider.getData(word)
}

class RoomDataBaseImplementation : DataSource<List<DataModel>> {
    override fun getData(word: String): Observable<List<DataModel>> {
        TODO("not implemented")
    }
}

