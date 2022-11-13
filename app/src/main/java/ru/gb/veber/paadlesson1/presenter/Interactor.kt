package ru.gb.veber.paadlesson1.presenter

import io.reactivex.Observable

interface Interactor<T> {
    fun getData(word: String, fromRemoteSources: Boolean): Observable<T>
}