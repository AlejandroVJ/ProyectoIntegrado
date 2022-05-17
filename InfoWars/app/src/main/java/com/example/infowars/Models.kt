package com.example.infowars

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import java.io.Serializable
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
    var raza: String,
    var apariciones: String,
    var img: String
    )


/**
 * Clase de Datos de facciones
 * Son clases que pueden estar dentro de otras clases en este caso de facciones
 * */
data class Faccion(var nombre: String, var era: String, //var anho: String,
                   var img: String
): Serializable {
    companion object {

        private val DEFAULT_COLOR = arrayOf(R.color.Gris, R.color.GrisClaro, R.drawable.ic_imperio)
        private val colores = mapOf(
            Pair("Republica Galactica", arrayOf(R.color.RepGalac, R.color.RepGalac2, R.drawable.ic_repg)),
            Pair(
                "Confederacion de Comercio",
                arrayOf(R.color.ConfederacionCom, R.color.ConfederacionCom2, R.drawable.ic_csi)
            ),
            Pair("Imperio Galáctico", arrayOf(R.color.ImperioGalac, R.color.ImperioGalac2, R.drawable.ic_imperio)),
            Pair("Rebelion", arrayOf(R.color.Rebelion, R.color.Rebelion2, R.drawable.ic_rebelion)),
            Pair("Primera Orden", arrayOf(R.color.PrimeraOrden, R.color.PrimeraOrden2, R.drawable.ic_primeraorden)),
            Pair("Resistencia", arrayOf(R.color.Resistencia, R.color.Resistencia2, R.drawable.ic_rebelion)),
            Pair("Cazarrecompensas", arrayOf(R.color.Cazarrecompensas, R.color.Cazarrecompensas2, R.drawable.ic_crr))
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

        @SuppressLint("NewApi")
        fun getIcono(faccionId: String): Int{
            val icono: Array<Int> = colores.getOrDefault(faccionId, DEFAULT_COLOR)
            return icono[2]
        }



    }


}
