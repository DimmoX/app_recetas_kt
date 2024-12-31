package com.example.apprecetas

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MenuOpen
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.apprecetas.views.Login
import com.example.apprecetas.views.RecipeScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    var selectedScreen by remember { mutableIntStateOf(0) }
    var isAuthenticated by remember { mutableStateOf(false) }

    // Nombres e íconos para las pantallas
    val screens = listOf("Login", "Recetas")
    val selectedIcons = listOf(Icons.Filled.Home, Icons.Filled.MenuOpen)
    val unselectedIcons = listOf(Icons.Outlined.Home, Icons.Outlined.MenuBook)

    Scaffold(
        bottomBar = {
            NavigationBar {
                screens.forEachIndexed { index, screen ->
                    NavigationBarItem(
                        icon = {
                            Icon(
                                if (selectedScreen == index) selectedIcons[index] else unselectedIcons[index],
                                contentDescription = screen
                            )
                        },
                        label = { Text(screen) },
                        selected = selectedScreen == index,
                        onClick = {
                            if (isAuthenticated || index == 0) {
                                // se permite la navegación solo si está autenticado
                                selectedScreen = index
                            }
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        // Espacio para el contenido principal según la pantalla seleccionada
        Box(
            Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (selectedScreen) {
                0 -> Login(onLoginSuccess = {
                    isAuthenticated = true // Cambiar el estado de autenticación
                    selectedScreen = 1 // Redirigir a Favoritos después del login
                })
                1 -> {
                    if (isAuthenticated) {
                        RecetasScreen()
                    } else {
                        // Se fuerza al usuario a volver al Login si no está autenticado
                        selectedScreen = 0
                    }
                }
            }
        }
    }
}

@Composable
fun screenLogin(onLoginSuccess: () -> Unit) {
    Box(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Login(Modifier.align(Alignment.Center), onLoginSuccess)
    }
}

@Composable
fun RecetasScreen() {
    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        RecipeScreen()
    }
}
