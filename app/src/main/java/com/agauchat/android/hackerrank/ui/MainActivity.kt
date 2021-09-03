package com.agauchat.android.hackerrank.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.agauchat.android.hackerrank.data.model.BookItem
import com.agauchat.android.hackerrank.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<BooksViewModel>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val booksAdapter = BooksAdapter()

        viewModel.books.observe(this) {
            booksAdapter.setBookList(it)
            binding.booksRecyclerView.setHasFixedSize(true)
            binding.booksRecyclerView.adapter = booksAdapter
        }

        val bestSellersBooksAdapter = BooksAdapter()

        viewModel.bestSellerBooks.observe(this) {
            if (it.isEmpty()) {
                binding.bestSellersTitle.visibility = View.GONE
                binding.bestSellersRecyclerView.visibility = View.GONE
            } else {
                bestSellersBooksAdapter.setBookList(it)
                binding.bestSellersRecyclerView.setHasFixedSize(true)
                binding.bestSellersRecyclerView.adapter = bestSellersBooksAdapter
            }
        }
    }
}