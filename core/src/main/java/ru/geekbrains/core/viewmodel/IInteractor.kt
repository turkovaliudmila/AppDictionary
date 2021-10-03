package ru.geekbrains.core.viewmodel

interface IInteractor<T> {
    suspend fun getData(word: String, fromRemoteSource: Boolean): T
}