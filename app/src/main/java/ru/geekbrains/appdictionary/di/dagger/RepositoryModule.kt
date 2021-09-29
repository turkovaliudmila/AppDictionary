package ru.geekbrains.appdictionary.di.dagger

import dagger.Module
import dagger.Provides
import ru.geekbrains.appdictionary.di.NAME_LOCAL
import ru.geekbrains.appdictionary.di.NAME_REMOTE
import ru.geekbrains.appdictionary.model.*
import javax.inject.Named
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    @Named(NAME_REMOTE)
    internal fun provideRepositoryRemote(@Named(NAME_REMOTE) dataSourceRemote: IDataSource<List<ItemOfDictionary>>): IRepository<List<ItemOfDictionary>> =
        RepositoryImplementation(dataSourceRemote)

    @Provides
    @Singleton
    @Named(NAME_REMOTE)
    internal fun provideDataSourceRemote(): IDataSource<List<ItemOfDictionary>> =
        RetrofitImplementation()

    @Provides
    @Singleton
    @Named(NAME_LOCAL)
    internal fun provideDataSourceLocal(): IDataSource<List<ItemOfDictionary>> = RoomDataBaseImplementation()
}