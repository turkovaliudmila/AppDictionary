package ru.geekbrains.appdictionary.model.database.repo

import ru.geekbrains.appdictionary.model.AppState
import ru.geekbrains.appdictionary.model.ItemOfDictionary
import ru.geekbrains.appdictionary.model.database.HistoryDao
import ru.geekbrains.appdictionary.model.database.HistoryEntity

class RoomDataBaseImplementation(private val historyDao: HistoryDao) :
    IDataSourceLocal<List<ItemOfDictionary>> {

    override suspend fun saveToDB(appState: AppState) {
        convertDataModelSuccessToEntity(appState)?.let {
            historyDao.insert(it)
        }
    }

    override suspend fun getData(word: String): List<ItemOfDictionary> {
        return mapHistoryEntityToSearchResult(historyDao.all())
    }

    private fun mapHistoryEntityToSearchResult(list: List<HistoryEntity>): List<ItemOfDictionary> {
        val dataModel = ArrayList<ItemOfDictionary>()
        if (!list.isNullOrEmpty()) {
            for (entity in list) {
                dataModel.add(ItemOfDictionary(entity.word, entity.phonetic, null))
            }
        }
        return dataModel
    }

    private fun convertDataModelSuccessToEntity(appState: AppState): HistoryEntity? {
        return when (appState) {
            is AppState.Success -> {
                val searchResult = appState.data
                if (searchResult.isNullOrEmpty() || searchResult[0].word.isNullOrEmpty()) {
                    null
                } else {
                    HistoryEntity(searchResult[0].word!!, searchResult[0].phonetic!!)
                }
            }
            else -> null
        }
    }

}