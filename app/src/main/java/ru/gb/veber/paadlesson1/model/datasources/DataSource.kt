package ru.gb.veber.paadlesson1.model.datasources

import io.reactivex.Observable
import ru.gb.veber.paadlesson1.model.datasources.network.DataModel

// Источник данных для репозитория (Интернет, БД и т. п.)
interface DataSource<T> {
    suspend fun getData(word: String): T
}
