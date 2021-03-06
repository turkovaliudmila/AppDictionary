package ru.geekbrains.appdictionary.viewmodel

import ru.geekbrains.appdictionary.di.NAME_REMOTE
import ru.geekbrains.appdictionary.model.AppState
import ru.geekbrains.appdictionary.model.IRepository
import ru.geekbrains.appdictionary.model.ItemOfDictionary
import ru.geekbrains.appdictionary.model.database.repo.IRepositoryLocal
import javax.inject.Named

class MainInteractor(
    private val repositoryRemote: IRepository<List<ItemOfDictionary>>,
    private val repositoryLocal: IRepositoryLocal<List<ItemOfDictionary>>
) : IInteractor<AppState> {
    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        val appState: AppState
        if (fromRemoteSource) {
            appState = AppState.Success(repositoryRemote.getData(word))
            repositoryLocal.saveToDB(appState)
        } else {
            appState = AppState.Success(repositoryLocal.getData(word))
        }
        return appState
    }
}
