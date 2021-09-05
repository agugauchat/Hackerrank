package com.agauchat.android.hackerrank.ui

sealed class BookRecyclerViewItem {

    class SectionTitle(
        val title: String,
        val count: Int
    ) : BookRecyclerViewItem()

    class BookCard(
        val title: String?,
        val author: String?,
        val img: String?
    ) : BookRecyclerViewItem()
}
