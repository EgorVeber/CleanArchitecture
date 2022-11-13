package ru.gb.veber.paadlesson1.model.datasources

import io.reactivex.Observable

interface DataSource<T> {
    fun getData(word: String): Observable<T>
}
