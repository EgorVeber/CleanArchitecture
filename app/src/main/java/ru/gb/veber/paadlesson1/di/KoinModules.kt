package ru.gb.veber.paadlesson1.di

import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.gb.veber.paadlesson1.model.MainInteractor
import ru.gb.veber.paadlesson1.model.datasources.RoomDataBaseImplementation
import ru.gb.veber.paadlesson1.model.datasources.network.DataModel
import ru.gb.veber.paadlesson1.model.repository.Repository
import ru.gb.veber.paadlesson1.model.repository.RepositoryImplementation
import ru.gb.veber.paadlesson1.model.repository.network.RetrofitImplementation
import ru.gb.veber.paadlesson1.viewmodel.MainViewModel

val application = module {
    single<Repository<List<DataModel>>>(named(NAME_REMOTE)) {
        RepositoryImplementation(RetrofitImplementation())
    }
    single<Repository<List<DataModel>>>(named(NAME_LOCAL)) {
        RepositoryImplementation(RoomDataBaseImplementation())
    }
}

val mainScreen = module {
    factory { MainInteractor(get(named(NAME_REMOTE)), get(named(NAME_LOCAL))) }
    factory { MainViewModel(interactor = get()) }
}