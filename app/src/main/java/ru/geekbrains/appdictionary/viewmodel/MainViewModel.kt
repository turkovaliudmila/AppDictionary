package ru.geekbrains.appdictionary.viewmodel

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.geekbrains.appdictionary.model.AppState

class MainViewModel(private val interactor: MainInteractor) : BaseViewModel<AppState>() {

    private var appState: AppState? = null

    private val liveDataForViewToObserve: LiveData<AppState> = _mutableLiveData

    fun getAppState(): LiveData<AppState> {
        return liveDataForViewToObserve
    }

    override fun getData(word: String) {
        _mutableLiveData.value = AppState.Loading(null)
        cancelJob()
        viewModelCoroutineScope.launch { startInteractor(word) }
    }

    private suspend fun startInteractor(word: String) = withContext(Dispatchers.IO) {
        _mutableLiveData.postValue(interactor.getData(word))
    }

    override fun handleError(error: Throwable) {
        _mutableLiveData.postValue(AppState.Error(error))
    }

    override fun onCleared() {
        _mutableLiveData.value = AppState.Success(null)
        super.onCleared()
    }

}