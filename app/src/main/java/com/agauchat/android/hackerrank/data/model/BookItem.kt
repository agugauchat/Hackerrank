package com.agauchat.android.hackerrank.data.model

data class BookItem(
    val isbn: String,
    val title: String?,
    val author: String?,
    val description: String?,
    val genre: String?,
    val img: String?,
    val imported: Boolean
)
