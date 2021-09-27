package ru.geekbrains.appdictionary.di.koin

import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.geekbrains.appdictionary.di.NAME_REMOTE
import ru.geekbrains.appdictionary.model.IRepository
import ru.geekbrains.appdictionary.model.ItemOfDictionary
import ru.geekbrains.appdictionary.model.RepositoryImplementation
import ru.geekbrains.appdictionary.model.RetrofitImplementation
import ru.geekbrains.appdictionary.viewmodel.MainInteractor
import ru.geekbrains.appdictionary.viewmodel.MainViewModel

val application = module {
    single<IRepository<List<ItemOfDictionary>>>(named(NAME_REMOTE)) {
        RepositoryImplementation(
            RetrofitImplementation()
        )
    }
}

val mainScreen = module {
    factory { MainInteractor(get(named(NAME_REMOTE))) }
    factory { MainViewModel(get()) }
}