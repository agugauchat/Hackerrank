package com.agauchat.android.hackerrank.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.agauchat.android.hackerrank.R
import com.agauchat.android.hackerrank.databinding.BookCardBinding
import com.agauchat.android.hackerrank.databinding.SectionTitleBinding
import java.lang.IllegalArgumentException

class BooksAdapter : RecyclerView.Adapter<BooksHolder>() {

    var items = listOf<BookRecyclerViewItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksHolder {
        return when (viewType) {
            R.layout.section_title -> BooksHolder.SectionTitleViewHolder(
                SectionTitleBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            R.layout.book_card -> BooksHolder.BookCardViewHolder(
                BookCardBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> throw IllegalArgumentException("Invalid viewType")
        }
    }

    override fun onBindViewHolder(holder: BooksHolder, position: Int) {
        when (holder) {
            is BooksHolder.SectionTitleViewHolder -> holder.bind(items[position] as BookRecyclerViewItem.SectionTitle)
            is BooksHolder.BookCardViewHolder -> holder.bind(items[position] as BookRecyclerViewItem.BookCard)
        }
    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is BookRecyclerViewItem.BookCard -> R.layout.book_card
            is BookRecyclerViewItem.SectionTitle -> R.layout.section_title
        }
    }
}