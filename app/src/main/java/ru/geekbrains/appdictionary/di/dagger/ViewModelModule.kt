package ru.geekbrains.appdictionary.di.dagger

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.geekbrains.appdictionary.di.dagger.InteractorModule
import ru.geekbrains.appdictionary.di.dagger.ViewModelFactory
import ru.geekbrains.appdictionary.di.dagger.ViewModelKey
import ru.geekbrains.appdictionary.viewmodel.MainViewModel

@Module(includes = [InteractorModule::class])
internal abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    protected abstract fun mainViewModel(mainViewModel: MainViewModel): ViewModel
}