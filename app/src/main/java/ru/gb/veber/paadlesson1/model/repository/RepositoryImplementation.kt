package ru.gb.veber.paadlesson1.model.repository

import io.reactivex.Observable
import ru.gb.veber.paadlesson1.model.datasources.network.DataModel
import ru.gb.veber.paadlesson1.model.datasources.DataSource

class RepositoryImplementation(private val dataSources: DataSource<List<DataModel>>) :
    Repository<List<DataModel>> {
    override fun getData(word: String): Observable<List<DataModel>> {
        return dataSources.getData(word)
    }
}