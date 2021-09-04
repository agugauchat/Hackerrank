package com.agauchat.android.hackerrank.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.agauchat.android.hackerrank.data.api.Resource
import com.agauchat.android.hackerrank.data.model.BookItem
import com.agauchat.android.hackerrank.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<BookViewModel>()
    private val bookRecyclerViewAdapter = BookRecyclerViewAdapter()
    private val bestSellersRecyclerViewAdapter = BookRecyclerViewAdapter()

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
                bestSellersRecyclerViewAdapter.items = result.value
            }
        }
    }
}

// ToDo -> Sacar recyclerView del nombre
// ToDo -> Probar rotando la pantalla
// ToDo -> Agregar readme: aclarando los nice to have
//Nice to have:
//Cache img -> Done con glide: https://futurestud.io/tutorials/glide-caching-basics
//Horizontal scroll de best sellers -> Done
// ToDo -> Ocultar los títulos si vienen vacías
// ToDo -> Correr los análisis de código
// ToDo -> Formatear el código y ordenar los xml
// ToDo -> Mover las dimensiones a dimen y también los textos
// ToDo -> Ver si puedo eliminar el bookResult
// ToDo -> Refactorizar lo que vea repetido