package ru.gb.veber.paadlesson1.model.datasources

import io.reactivex.Observable
import ru.gb.veber.paadlesson1.model.data.DataModel

class RoomDataBaseImplementation : DataSource<List<DataModel>> {
    override fun getData(word: String): Observable<List<DataModel>> {
        TODO("not implemented")
    }
}