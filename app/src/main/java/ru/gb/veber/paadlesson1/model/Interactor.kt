package ru.gb.veber.paadlesson1.model

import io.reactivex.Observable

interface Interactor<T> {
    fun getData(word: String, fromRemoteSources: Boolean): Observable<T>
}