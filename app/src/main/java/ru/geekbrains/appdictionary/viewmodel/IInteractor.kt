package ru.geekbrains.appdictionary.viewmodel

import io.reactivex.Observable

interface IInteractor<T> {
    fun getData(word: String): Observable<T>
}