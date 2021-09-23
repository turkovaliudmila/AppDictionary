package ru.geekbrains.appdictionary.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import ru.geekbrains.appdictionary.model.AppState
import ru.geekbrains.appdictionary.rx.SchedulerProvider

abstract class BaseViewModel<T : AppState> (
    protected open val liveDataForViewToObserve: MutableLiveData<T> = MutableLiveData(),
    protected open val compositeDisposable: CompositeDisposable = CompositeDisposable(),
    protected open val schedulerProvider: SchedulerProvider = SchedulerProvider()
    ) : ViewModel() {
        abstract fun getData(word: String)

        override fun onCleared() {
            compositeDisposable.clear()
        }
}