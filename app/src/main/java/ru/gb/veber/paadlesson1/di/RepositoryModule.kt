package ru.gb.veber.paadlesson1.di

import dagger.Module
import dagger.Provides
import ru.gb.veber.paadlesson1.model.datasources.DataSource
import ru.gb.veber.paadlesson1.model.datasources.RoomDataBaseImplementation
import ru.gb.veber.paadlesson1.model.datasources.network.DataModel
import ru.gb.veber.paadlesson1.model.repository.Repository
import ru.gb.veber.paadlesson1.model.repository.RepositoryImplementation
import ru.gb.veber.paadlesson1.model.repository.network.RetrofitImplementation
import javax.inject.Named
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    @Named(NAME_REMOTE)
    internal fun provideRepositoryRemote(@Named(NAME_REMOTE) dataSourceRemote: DataSource<List<DataModel>>): Repository<List<DataModel>> =
        RepositoryImplementation(dataSourceRemote)

    @Provides
    @Singleton
    @Named(NAME_LOCAL)
    internal fun provideRepositoryLocal(@Named(NAME_LOCAL) dataSourceLocal: DataSource<List<DataModel>>): Repository<List<DataModel>> =
        RepositoryImplementation(dataSourceLocal)

    @Provides
    @Singleton
    @Named(NAME_REMOTE)
    internal fun provideDataSourceRemote(): DataSource<List<DataModel>> =
        RetrofitImplementation()

    @Provides
    @Singleton
    @Named(NAME_LOCAL)
    internal fun provideDataSourceLocal(): DataSource<List<DataModel>> = RoomDataBaseImplementation()
}
