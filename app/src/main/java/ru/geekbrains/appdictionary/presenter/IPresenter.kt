package ru.geekbrains.appdictionary.presenter

import ru.geekbrains.appdictionary.model.AppState
import ru.geekbrains.appdictionary.view.IView

interface IPresenter<T: AppState, V: IView> {
    fun attachView(view: V)
    fun detachView(view: V)
    fun getData(word: String)
}