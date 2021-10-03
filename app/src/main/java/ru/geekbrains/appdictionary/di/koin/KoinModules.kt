package ru.geekbrains.appdictionary.di.koin

import androidx.room.Room
import org.koin.dsl.module
import ru.geekbrains.appdictionary.viewmodel.MainInteractor
import ru.geekbrains.appdictionary.viewmodel.MainViewModel
import ru.geekbrains.history.viewmodel.HistoryInteractor
import ru.geekbrains.history.viewmodel.HistoryViewModel
import ru.geekbrains.model.ItemOfDictionary

val application = module {
    single { Room.databaseBuilder(get(), ru.geekbrains.repository.database.HistoryDataBase::class.java, "HistoryDB").build() }
    single { get<ru.geekbrains.repository.database.HistoryDataBase>().historyDao() }
    single<ru.geekbrains.repository.IRepository<List<ItemOfDictionary>>> {
        ru.geekbrains.repository.RepositoryImplementation(
            ru.geekbrains.repository.RetrofitImplementation()
        )
    }
    single<ru.geekbrains.repository.database.repo.IRepositoryLocal<List<ItemOfDictionary>>> {
        ru.geekbrains.repository.database.repo.RepositoryImplementationLocal(
            ru.geekbrains.repository.database.repo.RoomDataBaseImplementation(get())
        )
    }
}

val mainScreen = module {
    factory { MainInteractor(get(), get()) }
    factory { MainViewModel(get()) }
}

val historyScreen = module {
    factory { HistoryViewModel(get()) }
    factory { HistoryInteractor(get(), get()) }
}