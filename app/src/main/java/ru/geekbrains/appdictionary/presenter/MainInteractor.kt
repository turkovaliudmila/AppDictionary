package ru.geekbrains.appdictionary.presenter

import io.reactivex.Observable
import ru.geekbrains.appdictionary.model.AppState
import ru.geekbrains.appdictionary.model.IRepository
import ru.geekbrains.appdictionary.model.ItemOfDictionary

class MainInteractor(
    private val remoteRepository: IRepository<List<ItemOfDictionary>>
) : IInteractor<AppState> {
    override fun getData(word: String): Observable<AppState> {
       return remoteRepository.getData(word).map { AppState.Success(it) }
    }
}
