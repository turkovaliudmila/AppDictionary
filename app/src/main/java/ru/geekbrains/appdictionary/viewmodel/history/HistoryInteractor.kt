package ru.geekbrains.appdictionary.viewmodel.history

import ru.geekbrains.appdictionary.model.AppState
import ru.geekbrains.appdictionary.model.IRepository
import ru.geekbrains.appdictionary.model.ItemOfDictionary
import ru.geekbrains.appdictionary.model.database.repo.IRepositoryLocal
import ru.geekbrains.appdictionary.viewmodel.IInteractor

class HistoryInteractor(
    private val repositoryRemote: IRepository<List<ItemOfDictionary>>,
    private val repositoryLocal: IRepositoryLocal<List<ItemOfDictionary>>
) : IInteractor<AppState> {
    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        return AppState.Success(
            if (fromRemoteSource) {
                repositoryRemote
            } else {
                repositoryLocal
            }.getData(word)
        )
    }
}