package ru.geekbrains.appdictionary.model

sealed class AppState {
    data class Success(val data: List<ItemOfDictionary>?) : AppState()
    data class Error(val error: Throwable) : AppState()
    data class Loading(val progress: Int?) : AppState()
}
