package ru.geekbrains.repository

interface IDataSource<T> {
    suspend fun getData(word: String): T
}