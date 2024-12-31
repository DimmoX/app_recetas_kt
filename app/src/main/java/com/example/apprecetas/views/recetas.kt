package com.example.apprecetas.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.apprecetas.models.Recetas
import com.example.apprecetas.models.RecipeViewModel

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

@Composable
fun ItemCard(item: Recetas) {
    // Estado para controlar la visibilidad del modal
    var isModalVisible by remember { mutableStateOf(false) }

    // Contenedor principal
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { isModalVisible = true } // Abre el modal al hacer clic
    ) {
        // Imagen
        Image(
            painter = painterResource(id = item.imageRes),
            contentDescription = item.title,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            contentScale = ContentScale.Crop
        )
        // Texto superpuesto en la parte Superior
        Box (
            modifier = Modifier
                .width(70.dp)
                .align(Alignment.TopStart)
                .background(Color.Red.copy(alpha = 0.6f))
                .padding(8.dp)
        ){
            Text(
                text = "Día ${item.id}",
                color = Color.White,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                //modifier = Modifier.fillMaxWidth()
            )
        }
        // Texto superpuesto en la parte Inferior
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .background(Color.Black.copy(alpha = 0.6f))
                .padding(8.dp)
        ) {
            Text(
                text = item.title,
                color = Color.White,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }

    // Modal con los pasos de preparación
    if (isModalVisible) {
        RecipeModal(item = item) { isModalVisible = false }
    }
}

@Composable
fun RecipeModal(item: Recetas, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = { onDismiss() }, // Cierra el modal
        title = {
            Text(text = item.title, style = MaterialTheme.typography.headlineSmall)
        },
        text = {
            // Habilitar desplazamiento vertical
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .verticalScroll(rememberScrollState()) // Scroll manual
            ) {
                // Tiempo de cocción
                Text(
                    text = "Tiempo: ${item.tiempo}",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                // Ingredientes
                Text(
                    text = "Ingredientes:",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                item.ingredientes.forEach { ingredient ->
                    Text(
                        text = "- $ingredient",
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(bottom = 2.dp)
                    )
                }

                // Pasos para la preparación
                Text(
                    text = "Pasos para preparación:",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                item.pasos.forEachIndexed { index, step ->
                    Text(
                        text = "${index + 1}.- $step",
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                }
            }
        },
        confirmButton = {
            TextButton(onClick = { onDismiss() }) {
                Text(text = "Cerrar")
            }
        }
    )
}