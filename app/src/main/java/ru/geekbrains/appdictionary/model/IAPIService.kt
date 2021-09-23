package ru.geekbrains.appdictionary.model

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface IAPIService {
    @GET("/api/v2/entries/en/{word}")
    fun search(@Path("word") wordToSearch: String): Observable<List<ItemOfDictionary>>
}