package ru.geekbrains.appdictionary.presenter

import io.reactivex.Observable

interface IInteractor<T> {
    fun getData(word: String): Observable<T>
}