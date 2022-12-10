package ru.gb.veber.paadlesson1.model

import ru.gb.veber.paadlesson1.di.NAME_LOCAL
import ru.gb.veber.paadlesson1.di.NAME_REMOTE
import io.reactivex.Observable
import ru.gb.veber.paadlesson1.model.datasources.network.DataModel
import ru.gb.veber.paadlesson1.model.repository.Repository
import javax.inject.Inject
import javax.inject.Named

class MainInteractor @Inject constructor(
    @Named(NAME_REMOTE) val remoteRepo: Repository<List<DataModel>>,
    @Named(NAME_LOCAL) val localRepo: Repository<List<DataModel>>
) : Interactor<AppState> {
    override fun getData(word: String, fromRemoteSources: Boolean): Observable<AppState> {
        return if (fromRemoteSources) {
            remoteRepo.getData(word).map { AppState.Success(it) }
        } else {
            localRepo.getData(word).map { AppState.Success(it) }
        }
    }
}