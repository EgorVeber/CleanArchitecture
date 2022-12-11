package ru.gb.veber.paadlesson1.model.reposotory

import ru.gb.veber.paadlesson1.model.data.AppState
import ru.gb.veber.paadlesson1.model.datasource.DataSource

interface Repository<T> {
    suspend fun getData(word: String): T
}

interface DataSourceLocal<T> : DataSource<T> {
    suspend fun saveToDB(appState: AppState)
}

interface RepositoryLocal<T> : Repository<T> {
    suspend fun saveToDB(appState: AppState)
}
