package ru.gb.veber.paadlesson1.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.gb.veber.paadlesson1.view.main.MainActivity

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}
