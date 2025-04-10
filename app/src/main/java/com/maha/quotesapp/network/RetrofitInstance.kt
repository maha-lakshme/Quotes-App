package com.maha.quotesapp.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val QUOTES_BASE_URL = "https://quoteslate.vercel.app/"

    // Define your OkHttpClient. For example, you might have logging or timeouts enabled.
    private val okHttpClient = OkHttpClient.Builder().build()

    private val quotesRetrofit: Retrofit by lazy {
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(QUOTES_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val quotesService: QuotesService by lazy {
        quotesRetrofit.create(QuotesService::class.java)
    }
}
