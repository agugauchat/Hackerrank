package com.agauchat.android.hackerrank.data.api

import javax.inject.Inject

class NetworkDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun getBooks() = apiService.getBooks()

    suspend fun getBestSellers() = apiService.getBestSellers()
}
