package com.agauchat.android.hackerrank.data.model

import com.google.gson.annotations.SerializedName

data class BooksResponse(
    @SerializedName("results") val booksResults: Books
)

data class Books(
    @SerializedName("books") val books: List<BookItem>
)
