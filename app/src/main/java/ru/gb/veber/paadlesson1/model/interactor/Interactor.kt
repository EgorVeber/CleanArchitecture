package ru.gb.veber.paadlesson1.model.interactor

interface Interactor<T> {
   suspend fun getData(word: String, fromRemoteSources: Boolean): T
}