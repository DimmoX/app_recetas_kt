package com.example.apprecetas.views

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.apprecetas.models.RecipeViewModel
import com.example.apprecetas.services.ItemCard

@Composable
fun RecipeScreen() {
    // Inicializa el ViewModel dentro de la función Composable
    val viewModel: RecipeViewModel = viewModel()

    // Obtén la lista de recetas desde el ViewModel
    val recetas = viewModel.recetas

    // Mostrar las recetas en una LazyColumn
    LazyColumn {
        items(recetas) { receta ->
            ItemCard(item = receta)
            println("ID: ${receta.id}, Título: ${receta.title}")
        }
    }
}
