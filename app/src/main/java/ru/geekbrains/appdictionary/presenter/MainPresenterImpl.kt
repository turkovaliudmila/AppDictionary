package ru.geekbrains.appdictionary.presenter

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import ru.geekbrains.appdictionary.model.AppState
import ru.geekbrains.appdictionary.model.DataSourceRemote
import ru.geekbrains.appdictionary.model.RepositoryImplementation
import ru.geekbrains.appdictionary.view.IView

class MainPresenterImpl<T : AppState, V : IView>(
    private val interactor: MainInteractor = MainInteractor(
        RepositoryImplementation(DataSourceRemote())
    ),
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
) : IPresenter<T, V> {

    private var currentView: V? = null

    override fun attachView(view: V) {
        if (view != currentView) {
            currentView = view
        }
    }

    override fun detachView(view: V) {
        compositeDisposable.clear()
        if (view == currentView) {
            currentView = null
        }
    }

    override fun getData(word: String) {
        compositeDisposable.add(
            interactor.getData(word)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { currentView?.renderData(AppState.Loading(null)) }
                .subscribeWith(getObserver())
        )
    }

    private fun getObserver(): DisposableObserver<AppState> {
        return object : DisposableObserver<AppState>() {

            override fun onNext(appState: AppState) {
                currentView?.renderData(appState)
            }

            override fun onError(e: Throwable) {
                currentView?.renderData(AppState.Error(e))
            }

            override fun onComplete() {
            }
        }
    }
}