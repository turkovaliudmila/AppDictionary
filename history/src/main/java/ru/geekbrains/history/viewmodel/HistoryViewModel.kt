package ru.geekbrains.history.viewmodel

import androidx.lifecycle.LiveData
import kotlinx.coroutines.launch
import ru.geekbrains.appdictionary.utils.parseLocalSearchResults
import ru.geekbrains.core.viewmodel.BaseViewModel
import ru.geekbrains.model.AppState

class HistoryViewModel(private val interactor: HistoryInteractor) :
    BaseViewModel<AppState>() {

    private val liveDataForViewToObserve: LiveData<ru.geekbrains.model.AppState> = _mutableLiveData

    fun getAppState(): LiveData<ru.geekbrains.model.AppState> {
        return liveDataForViewToObserve
    }

    override fun getData(word: String, isOnline: Boolean) {
        _mutableLiveData.value = ru.geekbrains.model.AppState.Loading(null)
        cancelJob()
        viewModelCoroutineScope.launch { startInteractor(word, isOnline) }
    }

    private suspend fun startInteractor(word: String, isOnline: Boolean) {
        _mutableLiveData.postValue(parseLocalSearchResults(interactor.getData(word, isOnline)))
    }

    override fun handleError(error: Throwable) {
        _mutableLiveData.postValue(ru.geekbrains.model.AppState.Error(error))
    }

    override fun onCleared() {
        _mutableLiveData.value = ru.geekbrains.model.AppState.Success(null) // Set View to
        super.onCleared()
    }

}