package ru.gb.veber.paadlesson1.core

import android.app.Application
import org.koin.core.context.GlobalContext.startKoin
import ru.gb.veber.paadlesson1.core.core.application
import ru.gb.veber.paadlesson1.core.core.mainScreen

class App : Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(application, mainScreen))
        }
    }
}
