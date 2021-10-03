package ru.geekbrains.appdictionary.viewmodel

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.geekbrains.core.viewmodel.BaseViewModel
import ru.geekbrains.model.AppState

class MainViewModel(private val interactor: MainInteractor) : BaseViewModel<AppState>() {

    private var appState: ru.geekbrains.model.AppState? = null

    private val liveDataForViewToObserve: LiveData<ru.geekbrains.model.AppState> = _mutableLiveData

    fun getLiveDataAppState(): LiveData<ru.geekbrains.model.AppState> {
        return liveDataForViewToObserve
    }

    override fun getData(word: String, isOnline: Boolean) {
        _mutableLiveData.value = ru.geekbrains.model.AppState.Loading(null)
        cancelJob()
        viewModelCoroutineScope.launch { startInteractor(word) }
    }

    private suspend fun startInteractor(word: String) = withContext(Dispatchers.IO) {
        _mutableLiveData.postValue(interactor.getData(word, true))
    }

    override fun handleError(error: Throwable) {
        _mutableLiveData.postValue(ru.geekbrains.model.AppState.Error(error))
    }

    override fun onCleared() {
        _mutableLiveData.value = ru.geekbrains.model.AppState.Success(null)
        super.onCleared()
    }

}