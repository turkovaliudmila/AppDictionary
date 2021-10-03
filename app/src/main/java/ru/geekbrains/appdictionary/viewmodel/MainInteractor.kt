package ru.geekbrains.appdictionary.viewmodel

import ru.geekbrains.core.viewmodel.IInteractor
import ru.geekbrains.model.AppState
import ru.geekbrains.model.ItemOfDictionary

class MainInteractor(
    private val repositoryRemote: ru.geekbrains.repository.IRepository<List<ItemOfDictionary>>,
    private val repositoryLocal: ru.geekbrains.repository.database.repo.IRepositoryLocal<List<ItemOfDictionary>>
) : IInteractor<AppState> {
    override suspend fun getData(
        word: String,
        fromRemoteSource: Boolean
    ): ru.geekbrains.model.AppState {
        val appState: ru.geekbrains.model.AppState
        if (fromRemoteSource) {
            appState = ru.geekbrains.model.AppState.Success(repositoryRemote.getData(word))
            repositoryLocal.saveToDB(appState)
        } else {
            appState = ru.geekbrains.model.AppState.Success(repositoryLocal.getData(word))
        }
        return appState
    }
}
