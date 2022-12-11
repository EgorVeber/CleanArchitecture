package ru.gb.veber.paadlesson1.core

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import ru.gb.veber.paadlesson1.core.di.application
import ru.gb.veber.paadlesson1.core.di.historyScreen
import ru.gb.veber.paadlesson1.core.di.mainScreen

class App : Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(listOf(application, mainScreen, historyScreen))
        }
    }
}
