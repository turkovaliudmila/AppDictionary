package ru.geekbrains.appdictionary.viewmodel

import ru.geekbrains.appdictionary.di.NAME_REMOTE
import ru.geekbrains.appdictionary.model.AppState
import ru.geekbrains.appdictionary.model.IRepository
import ru.geekbrains.appdictionary.model.ItemOfDictionary
import javax.inject.Named

class MainInteractor(
    @Named(NAME_REMOTE) val repositoryRemote: IRepository<List<ItemOfDictionary>>
) : IInteractor<AppState> {
    override suspend fun getData(word: String): AppState {
        return AppState.Success(repositoryRemote.getData(word))
    }
}
