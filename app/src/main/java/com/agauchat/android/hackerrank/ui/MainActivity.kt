package com.agauchat.android.hackerrank.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.agauchat.android.hackerrank.R
import com.agauchat.android.hackerrank.data.api.Resource
import com.agauchat.android.hackerrank.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<BookViewModel>()
    private val bookRecyclerViewAdapter = BooksAdapter()
    private val bestSellersRecyclerViewAdapter = BooksAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.booksRecyclerView.apply {
            setHasFixedSize(true)
            adapter = bookRecyclerViewAdapter
        }

        binding.bestSellersRecyclerView.apply {
            setHasFixedSize(true)
            adapter = bestSellersRecyclerViewAdapter
        }

        viewModel.bookListItemsLiveData.observe(this) { result ->
            if (result is Resource.Success) {
                bookRecyclerViewAdapter.items = result.value
            }
        }

        viewModel.bestSellersListItemsLiveData.observe(this) { result ->
            if (result is Resource.Success) {
                if (result.value.isNullOrEmpty()) {
                    changeBestSellersVisibility(binding, View.GONE)
                } else {
                    val bestSellersCount = result.value.count()
                    val detailText = this.resources.getQuantityString(
                        R.plurals.books_count,
                        bestSellersCount,
                        bestSellersCount
                    )
                    binding.bestSellersDetail.text = detailText
                    changeBestSellersVisibility(binding, View.VISIBLE)
                    bestSellersRecyclerViewAdapter.items = result.value
                }
            } else {
                changeBestSellersVisibility(binding, View.GONE)
            }
        }
    }

    private fun changeBestSellersVisibility(binding: ActivityMainBinding, visibilityState: Int) {
        binding.bestSellersTitleSection.visibility = visibilityState
        binding.bestSellersRecyclerView.visibility = visibilityState
    }
}
