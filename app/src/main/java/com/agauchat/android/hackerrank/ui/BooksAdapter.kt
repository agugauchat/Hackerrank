package com.agauchat.android.hackerrank.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.agauchat.android.hackerrank.data.model.BookItem
import com.agauchat.android.hackerrank.databinding.BookItemBinding
import com.agauchat.android.hackerrank.ui.utils.load

class BooksAdapter : RecyclerView.Adapter<BooksAdapter.BooksViewHolder>() {

    private lateinit var list: List<BookItem>

    fun setBookList(list: List<BookItem>) {
        this.list = list
        notifyDataSetChanged()
    }

    inner class BooksViewHolder(
        private val binding: BookItemBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(book: BookItem) {
            with(binding) {
                bookTitle.text = book.title
                bookAuthor.text = book.author
                bookImage.load(book.img)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksViewHolder {
        val binding =
            BookItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BooksViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: BooksViewHolder, position: Int) {
        holder.bind(list[position])
    }
}