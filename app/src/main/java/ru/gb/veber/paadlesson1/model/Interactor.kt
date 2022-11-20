package ru.gb.veber.paadlesson1.model

import io.reactivex.Observable

interface Interactor<T> {
   suspend fun getData(word: String, fromRemoteSources: Boolean): T
}