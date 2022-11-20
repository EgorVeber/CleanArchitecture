package ru.gb.veber.paadlesson1.model.repository


import ru.gb.veber.paadlesson1.model.datasources.DataSource
import ru.gb.veber.paadlesson1.model.datasources.network.DataModel

class RepositoryImplementation(private val dataSources: DataSource<List<DataModel>>) :
    Repository<List<DataModel>> {
    override suspend fun getData(word: String): List<DataModel> {
        return dataSources.getData(word)
    }
}