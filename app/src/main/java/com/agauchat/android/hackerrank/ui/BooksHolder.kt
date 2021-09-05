package com.agauchat.android.hackerrank.ui

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.agauchat.android.hackerrank.R
import com.agauchat.android.hackerrank.databinding.BookCardBinding
import com.agauchat.android.hackerrank.databinding.SectionTitleBinding
import com.agauchat.android.hackerrank.ui.utils.load

sealed class BooksHolder(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {

    class SectionTitleViewHolder(private val binding: SectionTitleBinding, private val context: Context) :
        BooksHolder(binding) {
        fun bind(sectionTitle: BookRecyclerViewItem.SectionTitle) {
            binding.sectionTitleName.text = sectionTitle.title
            val detailText = context.resources.getQuantityString(
                R.plurals.books_count,
                sectionTitle.count,
                sectionTitle.count
            )
            binding.sectionDetail.text = detailText
        }
    }

    class BookCardViewHolder(private val binding: BookCardBinding) : BooksHolder(binding) {
        fun bind(bookCard: BookRecyclerViewItem.BookCard) {
            with(binding) {
                bookTitle.text = bookCard.title
                bookAuthor.text = bookCard.author
                bookImage.load(bookCard.img)
            }
        }
    }
}
