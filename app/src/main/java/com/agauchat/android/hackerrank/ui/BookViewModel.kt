package com.agauchat.android.hackerrank.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agauchat.android.hackerrank.data.api.Resource
import com.agauchat.android.hackerrank.data.model.BookItem
import com.agauchat.android.hackerrank.data.repository.Repository
import com.agauchat.android.hackerrank.ui.utils.Genre
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _bookListItemsLiveData = MutableLiveData<Resource<List<BookRecyclerViewItem>>>()
    val bookListItemsLiveData: LiveData<Resource<List<BookRecyclerViewItem>>>
        get() = _bookListItemsLiveData

    private val _bestSellersListItemsLiveData =
        MutableLiveData<Resource<List<BookRecyclerViewItem>>>()
    val bestSellersListItemsLiveData: LiveData<Resource<List<BookRecyclerViewItem>>>
        get() = _bestSellersListItemsLiveData

    init {
        getListItems()
    }

    private fun getListItems() = viewModelScope.launch {
        _bookListItemsLiveData.postValue(Resource.Loading)
        _bestSellersListItemsLiveData.postValue(Resource.Loading)
        val booksDeferred = async { repository.getBooks() }
        val bestSellersDeferred = async { repository.getBestSellers() }

        val books = booksDeferred.await()
        val bestSellers = bestSellersDeferred.await()

        val bookItemsList = mutableListOf<BookRecyclerViewItem>()
        val bestSellersItemsList = mutableListOf<BookRecyclerViewItem>()

        if (books is Resource.Success) {
            val bookList = books.value.booksResults.books
            bookItemsList.addAll(getSectionItems(Genre.HISTORY, bookList))
            bookItemsList.addAll(getSectionItems(Genre.SCIENCE, bookList))
            bookItemsList.addAll(getSectionItems(Genre.BUSINESS, bookList))

            _bookListItemsLiveData.postValue(Resource.Success(bookItemsList))

            if (bestSellers is Resource.Success) {
                bestSellersItemsList.addAll(
                    getBestSellersItems(
                        bookList,
                        bestSellers.value.bestSellers.isbnList
                    )
                )
                _bestSellersListItemsLiveData.postValue(Resource.Success(bestSellersItemsList))
            }
        } else {
            Resource.Failure(false, null, null)
        }
    }

    private fun getBestSellersItems(
        bookList: List<BookItem>,
        bestSellers: List<String>
    ): MutableList<BookRecyclerViewItem> {
        val auxList = bookList.filter { book -> book.isbn in bestSellers }
        val items = mutableListOf<BookRecyclerViewItem>()
        auxList.forEach {
            items.add(BookRecyclerViewItem.BookCard(it.title, it.author, it.img))
        }

        return items
    }

    private fun getSectionItems(
        genre: Genre,
        bookList: List<BookItem>
    ): MutableList<BookRecyclerViewItem> {
        val auxList = bookList.filter { book -> book.genre == genre.title }
        val items = mutableListOf<BookRecyclerViewItem>()
        if (!auxList.isNullOrEmpty()) {
            items.add(BookRecyclerViewItem.SectionTitle(genre.title, auxList.count()))
            auxList.forEach {
                items.add(BookRecyclerViewItem.BookCard(it.title, it.author, it.img))
            }
        }

        return items
    }
}
