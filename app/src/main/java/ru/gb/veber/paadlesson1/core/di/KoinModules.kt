package ru.gb.veber.paadlesson1.core.di

import androidx.room.Room
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.gb.veber.paadlesson1.model.DataModel
import ru.gb.veber.paadlesson1.model.database.HistoryDataBase
import ru.gb.veber.paadlesson1.model.datasource.RetrofitImplementation
import ru.gb.veber.paadlesson1.model.datasource.RoomDataBaseImplementation
import ru.gb.veber.paadlesson1.model.interactor.HistoryInteractor
import ru.gb.veber.paadlesson1.model.interactor.MainInteractor
import ru.gb.veber.paadlesson1.model.reposotory.Repository
import ru.gb.veber.paadlesson1.model.reposotory.RepositoryImplementation
import ru.gb.veber.paadlesson1.model.reposotory.RepositoryImplementationLocal
import ru.gb.veber.paadlesson1.model.reposotory.RepositoryLocal
import ru.gb.veber.paadlesson1.view.history.HistoryViewModel
import ru.gb.veber.paadlesson1.view.main.MainViewModel

val application = module {

    // single указывает, что БД должна быть в единственном экземпляре
    single { Room.databaseBuilder(get(), HistoryDataBase::class.java,
        "HistoryDB").build() }

    single { get<HistoryDataBase>().historyDao() }

    single<Repository<List<DataModel>>> {
        RepositoryImplementation(RetrofitImplementation()) }

    single<RepositoryLocal<List<DataModel>>> {
        RepositoryImplementationLocal(RoomDataBaseImplementation(get()))
    }
}

val mainScreen = module {
    factory { MainInteractor(get(named(NAME_REMOTE)), get(named(NAME_LOCAL))) }
    factory { MainViewModel(interactor = get()) }
}

val historyScreen = module {
    factory { HistoryViewModel(get()) }
    factory { HistoryInteractor(get(), get()) }
}
