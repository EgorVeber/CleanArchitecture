package ru.gb.veber.paadlesson1.di

import dagger.Module
import dagger.Provides
import ru.gb.veber.paadlesson1.model.MainInteractor
import ru.gb.veber.paadlesson1.model.datasources.network.DataModel
import ru.gb.veber.paadlesson1.model.repository.Repository
import javax.inject.Named

@Module
class InteractorModule {

    @Provides
    internal fun provideInteractor(
        @Named(NAME_REMOTE) repositoryRemote: Repository<List<DataModel>>,
        @Named(NAME_LOCAL) repositoryLocal: Repository<List<DataModel>>
    ) = MainInteractor(repositoryRemote, repositoryLocal)
}
