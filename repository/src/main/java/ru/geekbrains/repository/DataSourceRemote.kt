package ru.geekbrains.repository

import ru.geekbrains.model.ItemOfDictionary

class DataSourceRemote(private val remoteProvider: RetrofitImplementation = RetrofitImplementation()) :
    IDataSource<List<ItemOfDictionary>> {
    override suspend fun getData(word: String): List<ru.geekbrains.model.ItemOfDictionary> =
        remoteProvider.getData(word)
}