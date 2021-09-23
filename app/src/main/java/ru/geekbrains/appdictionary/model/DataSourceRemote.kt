package ru.geekbrains.appdictionary.model

import io.reactivex.Observable

class DataSourceRemote(private val remoteProvider: RetrofitImplementation = RetrofitImplementation()) : IDataSource<List<ItemOfDictionary>> {
    override fun getData(word: String): Observable<List<ItemOfDictionary>> = remoteProvider.getData(word)
}