package ru.geekbrains.appdictionary.viewmodel

interface IInteractor<T> {
    suspend fun getData(word: String, fromRemoteSource: Boolean): T
}