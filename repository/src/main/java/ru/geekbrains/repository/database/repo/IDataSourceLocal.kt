package ru.geekbrains.repository.database.repo

interface IDataSourceLocal<T> : ru.geekbrains.repository.IDataSource<T> {
    suspend fun saveToDB(appState: ru.geekbrains.model.AppState)
}