package ru.geekbrains.appdictionary.model

class RepositoryImplementation(private val dataSourceRemote: IDataSource<List<ItemOfDictionary>>) :
    IRepository<List<ItemOfDictionary>> {
    override suspend fun getData(word: String): List<ItemOfDictionary> {
        return dataSourceRemote.getData(word)
    }
}