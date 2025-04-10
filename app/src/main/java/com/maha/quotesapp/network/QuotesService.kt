package com.maha.quotesapp.network


import com.maha.quotesapp.data.model.Quote
import retrofit2.http.GET
import retrofit2.http.Query

interface QuotesService {
    // Get random quotes without parameters
    @GET("api/quotes/random")
    suspend fun getQuotes(): List<Quote>

    // Get multiple random quotes using a count query parameter
    @GET("/api/quotes/random")
    suspend fun getMultipleRandomQuotes(@Query("count") count: Int): List<Quote>

    // Get quotes filtered by length range
    @GET("/api/quotes/random")
    suspend fun getQuoteByLength(
        @Query("minLength") minLength: Int,
        @Query("maxLength") maxLength: Int,
        @Query("count") count: Int
    ): List<Quote>

    // Get quotes by a specific author
    @GET("/api/quotes/random")
    suspend fun getQuoteByAuthor(@Query("author") author: String): List<Quote>

    // Get quotes filtered by tags
    @GET("/api/quotes/random")
    suspend fun getQuoteByTag(@Query("tags") tags: String): List<Quote>
}
