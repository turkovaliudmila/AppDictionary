package ru.geekbrains.appdictionary.di.koin

import androidx.room.Room
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.geekbrains.appdictionary.di.NAME_REMOTE
import ru.geekbrains.appdictionary.model.IRepository
import ru.geekbrains.appdictionary.model.ItemOfDictionary
import ru.geekbrains.appdictionary.model.RepositoryImplementation
import ru.geekbrains.appdictionary.model.RetrofitImplementation
import ru.geekbrains.appdictionary.model.database.HistoryDataBase
import ru.geekbrains.appdictionary.model.database.repo.IRepositoryLocal
import ru.geekbrains.appdictionary.model.database.repo.RepositoryImplementationLocal
import ru.geekbrains.appdictionary.model.database.repo.RoomDataBaseImplementation
import ru.geekbrains.appdictionary.viewmodel.MainInteractor
import ru.geekbrains.appdictionary.viewmodel.MainViewModel
import ru.geekbrains.appdictionary.viewmodel.history.HistoryInteractor
import ru.geekbrains.appdictionary.viewmodel.history.HistoryViewModel

val application = module {
    single { Room.databaseBuilder(get(), HistoryDataBase::class.java, "HistoryDB").build() }
    single { get<HistoryDataBase>().historyDao() }
    single<IRepository<List<ItemOfDictionary>>> { RepositoryImplementation(RetrofitImplementation()) }
    single<IRepositoryLocal<List<ItemOfDictionary>>> { RepositoryImplementationLocal(RoomDataBaseImplementation(get())) }
}

val mainScreen = module {
    factory { MainInteractor(get(), get()) }
    factory { MainViewModel(get()) }
}

val historyScreen = module {
    factory { HistoryViewModel(get()) }
    factory { HistoryInteractor(get(), get()) }
}