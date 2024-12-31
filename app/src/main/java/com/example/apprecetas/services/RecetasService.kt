package com.example.apprecetas.services

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType.Companion.Text
import androidx.compose.ui.unit.dp
import com.example.apprecetas.R
import com.example.apprecetas.models.Recetas
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.apprecetas.models.RecipeViewModel
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun ItemList(itemList: List<Recetas>) {
    LazyColumn {
        items(itemList) { item: Recetas ->
            ItemCard(item = item)
        }
    }

    // Imprimir IDs generados
    itemList.forEach {
        println("ID: ${it.id}, Título: ${it.title}")
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


