package com.maha.quotesapp.data.repository


import com.maha.quotesapp.data.model.Quote
import com.maha.quotesapp.network.QuotesService

class ContentRepository(private val quotesService: QuotesService) {

    // Fetch quotes from the API
    suspend fun fetchQuotes(): List<Quote> {
        return try {
            quotesService.getMultipleRandomQuotes(20)
        } catch (e: Exception) {
            // Handle exceptions as necessary – e.g., logging, error responses, etc.
            emptyList()
        }
    }
}
