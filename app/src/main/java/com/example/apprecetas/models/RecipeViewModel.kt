package com.example.apprecetas.models

import androidx.lifecycle.ViewModel
import com.example.apprecetas.R

class RecipeViewModel : ViewModel() {
    private var currentId = 0
    val recetas: List<Recetas> = listOf(
        Recetas(
            id = generateId(),
            imageRes = R.drawable.lasagna,
            title = "Lasagna",
            ingredientes = listOf(
                "Masa de lasagna",
                "Salsa blanca",
                "1 kg queso mantecoso",
                "500 cc crema",
                "Salsa de tomate",
                "500 gramos carne molida",
                "Sofrito (1 cebolla, 1 zanahoria, ajo a gusto)"
            ),
            pasos = listOf(
                "Precalienta el horno a 200 grados.",
                "Para la salsa de tomates: si lo haces con tomates naturales, debes molerlos. Echa la salsa en una olla, con la carne molida y el sofrito. Debes cocerla por media hora a fuego medio.",
                "En una fuente, cubre el fondo con salsa de tomate (primera capa).",
                "Agrega masas como segunda capa y cubre toda la fuente.",
                "Luego, haz lo mismo con el queso.",
                "La siguiente capa sería de masa y arriba de eso, salsa blanca.",
                "Anda intercalando masa con una capa de salsa de tomates, queso, salsa blanca y crema.",
                "Cubre la lasagna con queso y ponle un poco de queso rallado arriba.",
                "Pon la fuente en el horno y déjala por 30 minutos a 180 grados."
            ),
            tiempo = "1 hora 30 minutos"
        ),
        Recetas(
            id = generateId(),
            imageRes = R.drawable.pollo_mostaza,
            title = "Pollo a la Mostaza",
            ingredientes = listOf(
                "1 kg de pollo",
                "Mostaza",
                "Sal y pimienta",
                "Crema",
                "Hierbas aromáticas"
            ),
            pasos = listOf(
                "Marina el pollo con mostaza, sal y pimienta.",
                "Dora el pollo en una sartén caliente.",
                "Agrega la crema y las hierbas aromáticas.",
                "Cocina a fuego medio durante 20 minutos."
            ),
            tiempo = "45 minutos"
        ),
        Recetas(
            id = generateId(),
            imageRes = R.drawable.pollo_arvejado,
            title = "Pollo Arvejado",
            ingredientes = listOf(
                "4 porciones pollo",
                "1 cebolla",
                "1 zanahoria",
                "1 lata arvejas",
                "1 hoja laurel",
                "1 caldo de vegetales Maggi",
                "Orégano y pimentón",
                "Sal y pimienta",
                "2 dientes ajo"
            ),
            pasos = listOf(
                "Rehogar cebolla, ajo y zanahoria, todo picado. Luego incorporar el pollo. Salpimentar.",
                "Condimentar con orégano, pimentón, cubo Maggi. Añadir agua caliente, aproximado 2 dedos.",
                "Dejar cocinar tapado y cuando el pollo se vea cocido echar las arvejas. Cocinar unos minutos más y apagar. Listo para servir."
            ),
            tiempo = "1 hora"
        ),
        Recetas(
            id = generateId(),
            imageRes = R.drawable.fetuccini_alfredo,
            title = "Fetuccini con Salsa Alfredo",
            ingredientes = listOf(
                "2 cucharadas de mantequilla",
                "1 cucharada de aceite de oliva",
                "½ cdta de Ajo en Polvo Gourmet",
                "½ cdta de Cebolla en Polvo Gourmet",
                "2 tazas de crema",
                "1 ½ cucharada de queso crema (opcional)",
                "1 ¼ taza de queso parmesano rallado",
                "1 cucharada de Pimienta Blanca Molida Gourmet",
                " Sal de Mar Gourmet a gusto",
                "200 gr de jamón de pavo picado en tiras (pueden reemplazar por otro tipo de jamón)",
                "400 gr de fettucines",
                "Perejil fresco picado para servir"
            ),
            pasos = listOf(
                "En una olla, derretir la mantequilla junto al aceite, a fuego medio bajo. Agregar el ajo en polvo, cebolla en polvo y la crema; calentar hasta que empiecen a salir burbujas. Incorporar los quesos, revolver hasta que se hayan derretido. Sazonar con pimienta y sal a gusto. Añadir el jamón picado a la salsa.",
                "Cocinar la pasta siguiendo las instrucciones del paquete. Una vez listo, mezclar la salsa con los fetuccines. Servir inmediatamente, espolvoreado con perejil picado."
            ),
            tiempo = "15 minutos"
        ),
        Recetas(
            id = generateId(),
            imageRes = R.drawable.noquis_mechada,
            title = "Ñoquis con carne mechada",
            ingredientes = listOf(
                "800 g de sobrecostilla",
                "1 cebolla en corte pluma",
                "1 sobre de Base para Carne Mechada Gourmet",
                "Agua",
                "Vino blanco",
                "500 g de passata de tomate",
                "2 hojitas de laurel Gourmet",
                "Mix de Pimientas Gourmet a gusto",
                "Aceite de oliva a gusto",
                "400 g de ñoquis",
                "Queso parmesano para servir",
            ),
            pasos = listOf(
                "Calienta una olla con aceite de oliva y sella la carne en trozos, hasta que esté bien dorada.",
                "Añade la cebolla en corte pluma y cocina por 2 minutos o hasta que esté suave.",
                "Condimenta con mix de pimientas.",
                "Disuelve la base para carne mechada en agua fría y añade a la carne.",
                "Incorpora el vino y cocina a fuego bajo, mezclando de vez en cuando por 1,5 horas. En caso de que el líquido se absorba, puedes ir añadiendo más agua según lo necesite.",
                "Agrega la passata de tomates, el laurel y sigue cocinando a fuego bajo por 30-40 minutos más",
                "Una vez transcurrido el tiempo, desmenuza la carne con ayuda de una cuchara o tenedor.",
                "Incorpora los ñoquis recién cocidos, al dente y mezcla muy bien para impregnar con la salsa.",
                "Sirve de inmediato con queso parmesano."
            ),
            tiempo = "2 horas 10 minutos"
        )
    )

    private fun generateId(): Int {
        return ++currentId
    }
}
