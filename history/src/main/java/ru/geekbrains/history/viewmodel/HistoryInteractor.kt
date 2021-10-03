package ru.geekbrains.history.viewmodel

import ru.geekbrains.core.viewmodel.IInteractor
import ru.geekbrains.model.AppState
import ru.geekbrains.model.ItemOfDictionary

class HistoryInteractor(
    private val repositoryRemote: ru.geekbrains.repository.IRepository<List<ItemOfDictionary>>,
    private val repositoryLocal: ru.geekbrains.repository.database.repo.IRepositoryLocal<List<ItemOfDictionary>>
) : IInteractor<AppState> {
    override suspend fun getData(
        word: String,
        fromRemoteSource: Boolean
    ): ru.geekbrains.model.AppState {
        return ru.geekbrains.model.AppState.Success(
            if (fromRemoteSource) {
                repositoryRemote
            } else {
                repositoryLocal
            }.getData(word)
        )
    }
}