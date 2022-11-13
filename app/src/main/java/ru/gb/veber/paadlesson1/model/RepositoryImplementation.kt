package ru.gb.veber.paadlesson1.model

import io.reactivex.Observable
import ru.gb.veber.paadlesson1.myinterface.DataSource
import ru.gb.veber.paadlesson1.myinterface.Repository

class RepositoryImplementation(private val dataSources: DataSource<List<DataModel>>) :
    Repository<List<DataModel>> {
    override fun getData(word: String): Observable<List<DataModel>> {
        return dataSources.getData(word)
    }
}