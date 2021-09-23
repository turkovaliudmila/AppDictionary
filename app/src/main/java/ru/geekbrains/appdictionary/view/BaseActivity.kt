package ru.geekbrains.appdictionary.view

import androidx.appcompat.app.AppCompatActivity
import ru.geekbrains.appdictionary.model.AppState
import ru.geekbrains.appdictionary.viewmodel.BaseViewModel
import ru.geekbrains.appdictionary.viewmodel.IInteractor

abstract class BaseActivity<T : AppState, I : IInteractor<T>> : AppCompatActivity() {

    abstract val model: BaseViewModel<T>

    abstract fun renderData(appState: T)

}