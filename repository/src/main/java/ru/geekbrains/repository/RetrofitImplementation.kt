package ru.geekbrains.repository

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.geekbrains.model.ItemOfDictionary
import ru.geekbrains.repository.api.ApiInterceptor
import ru.geekbrains.repository.api.IAPIService

class RetrofitImplementation : IDataSource<List<ItemOfDictionary>> {
    override suspend fun getData(word: String): List<ru.geekbrains.model.ItemOfDictionary> {
        return getService(ApiInterceptor).searchAsync(word).await()
    }

    private fun getService(interceptor: Interceptor): IAPIService {
        return createRetrofit(interceptor).create(IAPIService::class.java)
    }

    private fun createRetrofit(interceptor: Interceptor): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_LOCATIONS)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(createOkHttpClient(interceptor))
            .build()
    }

    private fun createOkHttpClient(interceptor: Interceptor): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(interceptor)
        httpClient.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        return httpClient.build()
    }

    companion object {
        private const val BASE_URL_LOCATIONS = "https://api.dictionaryapi.dev"
    }
}