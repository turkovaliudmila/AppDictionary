package ru.geekbrains.repository.database.repo

interface IRepositoryLocal<T> : ru.geekbrains.repository.IRepository<T> {
    suspend fun saveToDB(appState: ru.geekbrains.model.AppState)
}