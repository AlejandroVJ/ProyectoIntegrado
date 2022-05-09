package com.example.infowars

import android.annotation.SuppressLint
import java.util.*


data class Personaje(
    var id: String = UUID.randomUUID().toString(),
    var nombre: String,
    var faccion: Faccion,
    var planeta: String,
    var nave: String,
    var fIconica: String,
    var lado: String,
    var armas: String,
    var raza: String
)

/**
 * Clase de Datos de facciones
 * Son clases que pueden estar dentro de otras clases en este caso de facciones
 * */
data class Faccion(var nombre: String, var era: String) {
    companion object {

        private val DEFAULT_COLOR = arrayOf(R.color.Gris, R.color.GrisClaro)
        private val colores = mapOf(
            Pair("Republica Galactica", arrayOf(R.color.RepGalac, R.color.RepGalac2)),
            Pair(
                "Confederacion de Comercio",
                arrayOf(R.color.ConfederacionCom, R.color.ConfederacionCom2)
            ),
            Pair("Imperio Galáctico", arrayOf(R.color.ImperioGalac, R.color.ImperioGalac2)),
            Pair("Rebelión", arrayOf(R.color.Rebelion, R.color.Rebelion2)),
            Pair("Primera Orden", arrayOf(R.color.PrimeraOrden, R.color.PrimeraOrden2)),
            Pair("Resistencia", arrayOf(R.color.Resistencia, R.color.Resistencia2)),
            Pair("Cazarrecompensas", arrayOf(R.color.Cazarrecompensas, R.color.Cazarrecompensas2))
        )

        @SuppressLint("NewApi")
        fun getColoresFaccion(faccionId: String): Int {
            val paleta: Array<Int> = colores.getOrDefault(faccionId, DEFAULT_COLOR)
            return paleta[0]

        }

        @SuppressLint("NewApi")
        fun getColoresFaccionOpaco(faccionId: String): Int{
            val paleta: Array<Int> = colores.getOrDefault(faccionId, DEFAULT_COLOR)
            return paleta[1]

        }
    }


}
