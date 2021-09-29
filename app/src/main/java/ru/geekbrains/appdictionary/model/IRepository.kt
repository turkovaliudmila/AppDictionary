package ru.geekbrains.appdictionary.model

interface IRepository<T> {
    suspend fun getData(word: String): T
}