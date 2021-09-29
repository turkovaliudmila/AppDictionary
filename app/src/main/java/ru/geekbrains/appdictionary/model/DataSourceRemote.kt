package ru.geekbrains.appdictionary.model

class DataSourceRemote(private val remoteProvider: RetrofitImplementation = RetrofitImplementation()) :
    IDataSource<List<ItemOfDictionary>> {
    override suspend fun getData(word: String): List<ItemOfDictionary> =
        remoteProvider.getData(word)
}