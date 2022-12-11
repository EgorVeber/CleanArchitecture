package ru.gb.veber.paadlesson1.model.reposotory

import ru.gb.veber.paadlesson1.model.data.DataModel
import ru.gb.veber.paadlesson1.model.datasource.DataSource


class RepositoryImplementation(private val dataSources: DataSource<List<DataModel>>) :
    Repository<List<DataModel>> {
    override suspend fun getData(word: String): List<DataModel> {
        return dataSources.getData(word)
    }
}