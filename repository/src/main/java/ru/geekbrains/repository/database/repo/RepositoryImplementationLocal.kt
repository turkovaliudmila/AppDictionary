package ru.geekbrains.repository.database.repo

import ru.geekbrains.model.ItemOfDictionary

class RepositoryImplementationLocal(private val dataSource: IDataSourceLocal<List<ItemOfDictionary>>) :
    IRepositoryLocal<List<ItemOfDictionary>> {
    override suspend fun saveToDB(appState: ru.geekbrains.model.AppState) {
        dataSource.saveToDB(appState)
    }

    override suspend fun getData(word: String): List<ru.geekbrains.model.ItemOfDictionary> {
        return dataSource.getData(word)
    }
}