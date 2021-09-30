package ru.geekbrains.appdictionary.model.database.repo

import ru.geekbrains.appdictionary.model.AppState
import ru.geekbrains.appdictionary.model.IRepository

interface IRepositoryLocal<T> : IRepository<T> {
    suspend fun saveToDB(appState: AppState)
}