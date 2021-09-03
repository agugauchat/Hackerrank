package com.agauchat.android.hackerrank.data.repository

import com.agauchat.android.hackerrank.data.api.NetworkDataSource
import com.agauchat.android.hackerrank.data.model.BestSellersResponse
import com.agauchat.android.hackerrank.data.model.BooksResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository @Inject constructor(
    private val networkDataSource: NetworkDataSource,
) : MainRepositoryInterface {

    override suspend fun getBooks(): BooksResponse {
        return networkDataSource.getBooks()
    }

    override suspend fun getBestSellers(): BestSellersResponse {
        return networkDataSource.getBestSellers()
    }
}
