package com.example.apprecetas.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.apprecetas.R
import com.example.apprecetas.models.Usuario

@Composable
fun Login(modifier: Modifier = Modifier, onLoginSuccess: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    var showRecoveryDialog by remember { mutableStateOf(false) }

    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeaderImage(Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.height(16.dp))
        EmailField(email, onEmailChange = { email = it })
        Spacer(modifier = Modifier.height(16.dp))
        PasswordField(password, onPasswordChange = { password = it })
        Spacer(modifier = Modifier.height(8.dp))

        // Recuperación de contraseña
        Text(
            text = "¿Olvidaste tu contraseña?",
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .clickable{ showRecoveryDialog = true }
                .padding(8.dp),
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botón de ingresar
        Button(
            onClick = {
                val validationMessage = validarCredenciales(email, password, usuarios)
                if (validationMessage === "true") {
                    onLoginSuccess() // Navegar a Favoritos si es válido
                } else {
                    errorMessage = validationMessage
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ingresar")
        }

        Text(
            text = errorMessage,
            color = Color.Red,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        if (showRecoveryDialog) {
            PasswordRecoveryDialog(
                usuarios = usuarios,
                onDismiss = { showRecoveryDialog = false }
            )
        }
    }
}

val usuarios = listOf(
    Usuario(nombre = "Jose", apellido = "Perez", email = "jperez@test.cl", passwd = "ABCD1234")
)

@Composable
fun HeaderImage(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.perfil),
        contentDescription = "Header",
        modifier = modifier
    )
}

@Composable
fun EmailField(email: String, onEmailChange: (String) -> Unit) {
    TextField(
        value = email,
        onValueChange = onEmailChange,
        placeholder = { Text("Ingrese su Email") },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(onNext = { /* Puedes manejar acciones adicionales */ })
    )
}

@Composable
fun PasswordField(password: String, onPasswordChange: (String) -> Unit) {
    TextField(
        value = password,
        onValueChange = onPasswordChange,
        placeholder = { Text("Ingrese su Contraseña") },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(onDone = { /* Puedes manejar acciones adicionales */ })
    )
}

@Composable
fun PasswordRecoveryDialog(usuarios: List<Usuario>, onDismiss: () -> Unit) {
    var recoveryEmail by remember { mutableStateOf("") }
    var recoveryMessage by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Recuperar Contraseña") },
        text = {
            Column {
                TextField(
                    value = recoveryEmail,
                    onValueChange = { recoveryEmail = it },
                    placeholder = { Text("Ingrese su Email") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = { /* No hace nada en este caso */ }
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = recoveryMessage,
                    color = if (recoveryMessage.contains("No se encontró")) Color.Red else Color.Green,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        },
        confirmButton = {
            Button(onClick = {
                val usuario = usuarios.find { it.email == recoveryEmail }
                recoveryMessage = if (usuario != null) {
                    "Tu contraseña es: ${usuario.passwd}"
                } else {
                    "No se encontró una cuenta con este email."
                }
            }) {
                Text("Recuperar")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cerrar")
            }
        }
    )
}

fun validarCredenciales(email: String, password: String, usuarios: List<Usuario>): String {
    if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
        return "El email ingresado no es válido"
    }
    if (password.length < 8) {
        return "La contraseña debe tener al menos 8 caracteres"
    }
    val usuario = usuarios.find { it.email == email && it.passwd == password }
    return if (usuario != null) {
       "true"
    } else {
        "Credenciales incorrectas"
    }
}

