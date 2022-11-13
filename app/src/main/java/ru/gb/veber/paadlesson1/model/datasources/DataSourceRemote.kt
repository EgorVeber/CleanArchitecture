package ru.gb.veber.paadlesson1.model.datasources

import io.reactivex.Observable
import ru.gb.veber.paadlesson1.model.repository.RetrofitImplementation
import ru.gb.veber.paadlesson1.model.data.DataModel

class DataSourceRemote(
    private val remoteProvider: RetrofitImplementation =
        RetrofitImplementation(),
) :
    DataSource<List<DataModel>> {
    override fun getData(word: String): Observable<List<DataModel>> =
        remoteProvider.getData(word)
}