package ru.gb.veber.paadlesson1.model.repository

interface Repository<T> {
    suspend fun getData(word: String): T
}
