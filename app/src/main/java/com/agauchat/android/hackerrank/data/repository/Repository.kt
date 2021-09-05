package com.agauchat.android.hackerrank.data.repository

import com.agauchat.android.hackerrank.data.api.ApiService
import com.agauchat.android.hackerrank.data.api.SafeApiCall
import javax.inject.Inject

class Repository @Inject constructor(private val api: ApiService) : SafeApiCall {
    suspend fun getBooks() = safeApiCall { api.getBooks() }
    suspend fun getBestSellers() = safeApiCall { api.getBestSellers() }
}
