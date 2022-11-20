package ru.gb.veber.paadlesson1.model.datasources

import io.reactivex.Observable
import ru.gb.veber.paadlesson1.model.datasources.network.DataModel
import ru.gb.veber.paadlesson1.model.repository.network.RetrofitImplementation

class DataSourceRemote(
    private val remoteProvider: RetrofitImplementation =
        RetrofitImplementation(),
) : DataSource<List<DataModel>> {
    override suspend fun getData(word: String): List<DataModel> =
        remoteProvider.getData(word)
}