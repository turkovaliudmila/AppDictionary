package ru.geekbrains.appdictionary.di

import dagger.Module
import dagger.Provides
import ru.geekbrains.appdictionary.model.IRepository
import ru.geekbrains.appdictionary.model.ItemOfDictionary
import ru.geekbrains.appdictionary.viewmodel.MainInteractor
import javax.inject.Named

@Module
class InteractorModule {
    @Provides
    internal fun provideInteractor(
        @Named(NAME_REMOTE) repositoryRemote: IRepository<List<ItemOfDictionary>>
    ) = MainInteractor(repositoryRemote)
}