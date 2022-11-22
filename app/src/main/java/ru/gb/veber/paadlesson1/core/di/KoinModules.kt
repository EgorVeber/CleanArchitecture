package ru.gb.veber.paadlesson1.core.core

import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.gb.veber.paadlesson1.model.interactor.MainInteractor
import ru.gb.veber.paadlesson1.model.datasource.RoomDataBaseImplementation
import ru.gb.veber.paadlesson1.model.DataModel
import ru.gb.veber.paadlesson1.model.reposotory.Repository
import ru.gb.veber.paadlesson1.model.reposotory.RepositoryImplementation
import ru.gb.veber.paadlesson1.model.datasource.RetrofitImplementation
import ru.gb.veber.paadlesson1.view.main.MainViewModel

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