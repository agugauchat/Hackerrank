package com.agauchat.android.hackerrank.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.agauchat.android.hackerrank.data.model.BookItem
import com.agauchat.android.hackerrank.data.model.BooksResponse
import com.agauchat.android.hackerrank.data.repository.MainRepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class BooksViewModel @Inject constructor(private val mainRepository: MainRepositoryInterface) :
    ViewModel() {
    val books = liveData(Dispatchers.IO) {
        emit(mainRepository.getBooks().booksResults.books)
    }

    val bestSellerBooks: LiveData<List<BookItem>> = liveData {
        val bestSellers = mainRepository.getBestSellers().bestSellers.isbnList
        //val books = mainRepository.getBooks().booksResults.books
        emit(books.value().books.filter { book -> book.isbn in bestSellers })
    }
}
