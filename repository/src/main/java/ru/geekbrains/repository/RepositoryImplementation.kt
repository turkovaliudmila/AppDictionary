package ru.geekbrains.repository

import ru.geekbrains.model.ItemOfDictionary

class RepositoryImplementation(private val dataSourceRemote: IDataSource<List<ItemOfDictionary>>) :
    IRepository<List<ItemOfDictionary>> {
    override suspend fun getData(word: String): List<ru.geekbrains.model.ItemOfDictionary> {
        return dataSourceRemote.getData(word)
    }
}