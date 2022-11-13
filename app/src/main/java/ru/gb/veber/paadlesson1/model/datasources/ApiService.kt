package ru.gb.veber.paadlesson1.model.datasources

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import ru.gb.veber.paadlesson1.model.data.DataModel

interface ApiService {
    @GET("words/search")
    fun search(@Query("search") wordToSearch: String): Observable<List<DataModel>>
}