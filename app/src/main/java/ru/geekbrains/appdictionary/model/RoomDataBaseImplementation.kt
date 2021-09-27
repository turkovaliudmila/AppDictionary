package ru.geekbrains.appdictionary.model

import io.reactivex.Observable

class RoomDataBaseImplementation : IDataSource<List<ItemOfDictionary>> {
    override fun getData(word: String): Observable<List<ItemOfDictionary>> {
        TODO("Not yet implemented")
    }
}