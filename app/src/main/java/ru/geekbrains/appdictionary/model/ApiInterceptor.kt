package ru.geekbrains.appdictionary.model

import okhttp3.Interceptor
import okhttp3.Response

object ApiInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response =
        chain.proceed(
            chain.request()
                .newBuilder()
                .build()
        )
}