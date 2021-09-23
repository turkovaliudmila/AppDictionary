package ru.geekbrains.appdictionary.view

import ru.geekbrains.appdictionary.model.AppState

interface IView {
    fun renderData(appState: AppState)
}