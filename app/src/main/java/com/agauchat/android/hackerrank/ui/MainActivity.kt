package com.agauchat.android.hackerrank.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
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
                    binding.bestSellersTitle.visibility = View.GONE
                    binding.bestSellersRecyclerView.visibility = View.GONE
                } else {
                    binding.bestSellersTitle.visibility = View.VISIBLE
                    binding.bestSellersRecyclerView.visibility = View.VISIBLE
                    bestSellersRecyclerViewAdapter.items = result.value
                }
            } else {
                binding.bestSellersTitle.visibility = View.GONE
                binding.bestSellersRecyclerView.visibility = View.GONE
            }
        }
    }
}

// ToDo -> Función para cambiar la visibility
// Done -> Sacar recyclerView del nombre
// Done -> Ocultar los títulos si vienen vacías
// Done -> Refactorizar lo que vea repetido
// Fail -> Ver si puedo eliminar el bookResult: No pude
// Done -> Acomodar UI
// Done -> Probar rotando la pantalla y sin conexión
// Done -> Ordenar los xml
// ToDO -> Mover el nombre de las categorías a un enum
// ToDo -> Agregar readme: aclarando los nice to have
    //Nice to have:
    //Cache img -> Done con glide: https://futurestud.io/tutorials/glide-caching-basics
    //Horizontal scroll de best sellers -> Done
// ToDo -> Correr los análisis de código
// ToDo -> Formatear el código y ordenar los xml: TERMINAR
// ToDo -> Mover las dimensiones a dimen y también los textos