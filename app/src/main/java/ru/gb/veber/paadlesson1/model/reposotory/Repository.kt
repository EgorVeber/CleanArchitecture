package ru.gb.veber.paadlesson1.model.reposotory

interface Repository<T> {
    suspend fun getData(word: String): T
}
