package ru.geekbrains.appdictionary.model

import io.reactivex.Observable

class RepositoryImplementation(private val dataSourceRemote: IDataSource<List<ItemOfDictionary>>) : IRepository<List<ItemOfDictionary>> {
    override fun getData(word: String): Observable<List<ItemOfDictionary>> {
        return dataSourceRemote.getData(word)
    }
}