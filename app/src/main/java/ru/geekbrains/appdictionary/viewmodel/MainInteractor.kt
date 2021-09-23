package ru.geekbrains.appdictionary.viewmodel

import io.reactivex.Observable
import ru.geekbrains.appdictionary.di.NAME_REMOTE
import ru.geekbrains.appdictionary.model.AppState
import ru.geekbrains.appdictionary.model.IRepository
import ru.geekbrains.appdictionary.model.ItemOfDictionary
import ru.geekbrains.appdictionary.viewmodel.IInteractor
import javax.inject.Inject
import javax.inject.Named

class MainInteractor @Inject constructor(
    @Named(NAME_REMOTE) val repositoryRemote: IRepository<List<ItemOfDictionary>>
) : IInteractor<AppState> {
    override fun getData(word: String): Observable<AppState> {
       return repositoryRemote.getData(word).map { AppState.Success(it) }
    }
}
