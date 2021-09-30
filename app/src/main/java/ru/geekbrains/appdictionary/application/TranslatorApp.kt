package ru.geekbrains.appdictionary.application

import android.app.Application

import ru.geekbrains.appdictionary.di.koin.application
import ru.geekbrains.appdictionary.di.koin.historyScreen
import ru.geekbrains.appdictionary.di.koin.mainScreen
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class TranslatorApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(listOf(application, mainScreen, historyScreen))
        }
    }
}