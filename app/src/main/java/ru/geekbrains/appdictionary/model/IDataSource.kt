package ru.geekbrains.appdictionary.model

interface IDataSource<T> {
    suspend fun getData(word: String): T
}