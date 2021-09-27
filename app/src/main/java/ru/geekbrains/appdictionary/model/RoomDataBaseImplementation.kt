package ru.geekbrains.appdictionary.model

class RoomDataBaseImplementation : IDataSource<List<ItemOfDictionary>> {
    override suspend fun getData(word: String): List<ItemOfDictionary> {
        TODO("Not yet implemented")
    }
}