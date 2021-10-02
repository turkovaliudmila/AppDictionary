package ru.geekbrains.appdictionary.model.database.repo

import ru.geekbrains.appdictionary.model.AppState
import ru.geekbrains.appdictionary.model.IDataSource

interface IDataSourceLocal<T> : IDataSource<T> {
    suspend fun saveToDB(appState: AppState)
}