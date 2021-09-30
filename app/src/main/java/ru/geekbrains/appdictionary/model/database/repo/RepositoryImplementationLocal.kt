package ru.geekbrains.appdictionary.model.database.repo

import ru.geekbrains.appdictionary.model.AppState
import ru.geekbrains.appdictionary.model.ItemOfDictionary

class RepositoryImplementationLocal(private val dataSource: IDataSourceLocal<List<ItemOfDictionary>>) :
    IRepositoryLocal<List<ItemOfDictionary>> {
    override suspend fun saveToDB(appState: AppState) {
        dataSource.saveToDB(appState)
    }

    override suspend fun getData(word: String): List<ItemOfDictionary> {
        return dataSource.getData(word)
    }
}