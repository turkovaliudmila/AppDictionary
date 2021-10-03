package ru.geekbrains.repository.database.repo

import ru.geekbrains.model.ItemOfDictionary
import ru.geekbrains.repository.database.HistoryDao
import ru.geekbrains.repository.database.HistoryEntity

class RoomDataBaseImplementation(private val historyDao: HistoryDao) :
    IDataSourceLocal<List<ItemOfDictionary>> {

    override suspend fun saveToDB(appState: ru.geekbrains.model.AppState) {
        convertDataModelSuccessToEntity(appState)?.let {
            historyDao.insert(it)
        }
    }

    override suspend fun getData(word: String): List<ru.geekbrains.model.ItemOfDictionary> {
        return mapHistoryEntityToSearchResult(historyDao.all())
    }

    private fun mapHistoryEntityToSearchResult(list: List<HistoryEntity>): List<ru.geekbrains.model.ItemOfDictionary> {
        val dataModel = ArrayList<ru.geekbrains.model.ItemOfDictionary>()
        if (!list.isNullOrEmpty()) {
            for (entity in list) {
                dataModel.add(
                    ru.geekbrains.model.ItemOfDictionary(
                        entity.word,
                        entity.phonetic,
                        null
                    )
                )
            }
        }
        return dataModel
    }

    private fun convertDataModelSuccessToEntity(appState: ru.geekbrains.model.AppState): HistoryEntity? {
        return when (appState) {
            is ru.geekbrains.model.AppState.Success -> {
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