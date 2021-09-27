package ru.geekbrains.appdictionary.application

import android.app.Application
import org.koin.core.context.startKoin
import ru.geekbrains.appdictionary.di.koin.application
import ru.geekbrains.appdictionary.di.koin.mainScreen

class TranslatorApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin { modules(listOf(application, mainScreen)) }
    }
}