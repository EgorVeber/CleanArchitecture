package ru.gb.veber.paadlesson1

import android.app.Application
import org.koin.core.context.GlobalContext.startKoin
import ru.gb.veber.paadlesson1.di.application
import ru.gb.veber.paadlesson1.di.mainScreen

class TranslatorApp : Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(application, mainScreen))
        }
    }
}
