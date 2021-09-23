package ru.geekbrains.appdictionary.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.geekbrains.appdictionary.view.MainActivity

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}