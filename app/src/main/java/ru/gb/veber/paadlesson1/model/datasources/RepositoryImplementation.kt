package ru.gb.veber.paadlesson1.model.datasources

import io.reactivex.Observable
import ru.gb.veber.paadlesson1.model.data.DataModel
import ru.gb.veber.paadlesson1.model.repository.Repository

class RepositoryImplementation(private val dataSources: DataSource<List<DataModel>>) :
    Repository<List<DataModel>> {
    override fun getData(word: String): Observable<List<DataModel>> {
        return dataSources.getData(word)
    }
}