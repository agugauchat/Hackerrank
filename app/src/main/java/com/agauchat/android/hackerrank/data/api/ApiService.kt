package com.agauchat.android.hackerrank.data.api

import com.agauchat.android.hackerrank.data.model.BestSellersResponse
import com.agauchat.android.hackerrank.data.model.BooksResponse
import retrofit2.http.GET

private const val GET_BOOKS = "books.json"
private const val GET_BEST_SELLERS = "best_sellers.json"

interface ApiService {
    companion object {
        const val BASE_URL = "https://raw.githubusercontent.com/ejgteja/files/main/"
    }

    @GET(GET_BOOKS)
    suspend fun getBooks(): BooksResponse

    @GET(GET_BEST_SELLERS)
    suspend fun getBestSellers(): BestSellersResponse
}
