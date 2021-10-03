package ru.geekbrains.repository.api

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface IAPIService {
    @GET("/api/v2/entries/en/{word}")
    fun searchAsync(@Path("word") wordToSearch: String): Deferred<List<ru.geekbrains.model.ItemOfDictionary>>
}