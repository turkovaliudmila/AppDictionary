package ru.geekbrains.appdictionary.viewmodel.history

import androidx.lifecycle.LiveData
import kotlinx.coroutines.launch
import ru.geekbrains.appdictionary.model.AppState
import ru.geekbrains.appdictionary.utils.parseLocalSearchResults
import ru.geekbrains.appdictionary.viewmodel.BaseViewModel

class HistoryViewModel(private val interactor: HistoryInteractor) :
    BaseViewModel<AppState>() {

    private val liveDataForViewToObserve: LiveData<AppState> = _mutableLiveData

    fun getAppState(): LiveData<AppState> {
        return liveDataForViewToObserve
    }

    override fun getData(word: String, isOnline: Boolean) {
        _mutableLiveData.value = AppState.Loading(null)
        cancelJob()
        viewModelCoroutineScope.launch { startInteractor(word, isOnline) }
    }

    private suspend fun startInteractor(word: String, isOnline: Boolean) {
        _mutableLiveData.postValue(parseLocalSearchResults(interactor.getData(word, isOnline)))
    }

    override fun handleError(error: Throwable) {
        _mutableLiveData.postValue(AppState.Error(error))
    }

    override fun onCleared() {
        _mutableLiveData.value = AppState.Success(null) // Set View to
        super.onCleared()
    }

}