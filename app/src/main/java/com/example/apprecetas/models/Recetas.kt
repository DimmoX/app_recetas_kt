package com.example.apprecetas.models

data class Recetas(
    val id: Int = generateId(),
    val imageRes: Int,
    val title: String,
    val ingredientes: List<String>,
    val pasos: List<String>,
    val tiempo: String
){
    companion object {
        private var currentId = 0
        private fun generateId(): Int {
            return ++currentId
        }
    }
}
