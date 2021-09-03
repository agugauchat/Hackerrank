package com.agauchat.android.hackerrank.data.repository

import com.agauchat.android.hackerrank.data.model.BestSellersResponse
import com.agauchat.android.hackerrank.data.model.BooksResponse

interface MainRepositoryInterface {

    suspend fun getBooks(): BooksResponse

    suspend fun getBestSellers(): BestSellersResponse
}
