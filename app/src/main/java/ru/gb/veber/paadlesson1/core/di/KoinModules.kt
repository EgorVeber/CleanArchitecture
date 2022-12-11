package ru.gb.veber.paadlesson1.core.di

import androidx.room.Room
import org.koin.dsl.module
import ru.gb.veber.model.data.DataModel
import ru.gb.veber.paadlesson1.model.database.HistoryDataBase
import ru.gb.veber.paadlesson1.model.datasource.RetrofitImplementation
import ru.gb.veber.paadlesson1.model.datasource.RoomDataBaseImplementation
import ru.gb.veber.paadlesson1.model.interactor.HistoryInteractor
import ru.gb.veber.paadlesson1.model.interactor.MainInteractor
import ru.gb.veber.paadlesson1.model.interactor.SearchHistoryDialogInteractor
import ru.gb.veber.paadlesson1.model.reposotory.Repository
import ru.gb.veber.paadlesson1.model.reposotory.RepositoryImplementation
import ru.gb.veber.paadlesson1.model.reposotory.RepositoryImplementationLocal
import ru.gb.veber.paadlesson1.model.reposotory.RepositoryLocal
import ru.gb.veber.paadlesson1.view.history.HistoryViewModel
import ru.gb.veber.paadlesson1.view.historydialog.SearchHistoryDialogViewModel
import ru.gb.veber.paadlesson1.view.main.MainViewModel

val application = module {
    single { Room.databaseBuilder(get(), HistoryDataBase::class.java, "HistoryDB").build() }
    single { get<HistoryDataBase>().historyDao() }
    single<Repository<List<DataModel>>> { RepositoryImplementation(RetrofitImplementation()) }
    single<RepositoryLocal<List<DataModel>>> {
        RepositoryImplementationLocal(RoomDataBaseImplementation(get()))
    }
}

val mainScreen = module {
    factory { MainViewModel(get()) }
    factory { MainInteractor(get(), get()) }
}

val historyScreen = module {
    factory { HistoryViewModel(get()) }
    factory { HistoryInteractor(get(), get()) }
}

val dialogHistory = module {
    factory { SearchHistoryDialogViewModel(get()) }
    factory { SearchHistoryDialogInteractor(get()) }
}
