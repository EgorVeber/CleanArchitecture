package ru.gb.veber.paadlesson1.model.datasource

interface DataSource<T> {
    suspend fun getData(word: String): T
}
