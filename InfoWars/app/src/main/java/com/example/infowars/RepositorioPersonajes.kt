package com.example.infowars

import java.util.*

object RepositorioPersonajes {

    var personajes: MutableList<Personaje> = mutableListOf()

    get() {
        if (field.isEmpty())
            field.addAll(personajesPrueba())
        return field
    }

    /**
     * Método para generarnos personajes de "prueba"
     * Este método es provisional hasta que ya hayan personajes en nuestra aplicación
     * */
    private fun personajesPrueba(): MutableList<Personaje>{

        return (1..10).map {
            intToPersonaje(it)

        }.toMutableList()

    }

     fun findPersById(id: String?): Personaje? {
        return personajes.find { personaje -> personaje.id == id }
    }

    private fun intToPersonaje(int:Int): Personaje{
       return Personaje(
            nombre = "Personaje ${int}",
            planeta = "Planeta ${int}",
            nave = "Nave ${int}",
            fIconica = "Frase icónica ${int}",
            lado = "Lado ${int}",
            armas = "Arma ${int}",
            raza = "Raza ${int}",
            faccion = Facciones()
        )
    }

    /**
     * Metodo para generar facciones aleatorias
     * Este método es provisional hasta que ya hayan personajes almacenados en nuestra aplicación
     * Este metodo de prueba es para comprobar que los colores se asignan correctamente a cada facción
     * */
    private fun Facciones(): Faccion {
        val id = arrayOf("Republica Galactica", "Confederacion de Comercio", "Imperio Galáctico", "Rebelión", "Primera Orden", "Resistencia", "Cazarrecompensas")
        val randomId = Random().nextInt(id.size)

        return Faccion(nombre = id[randomId], era = "Era")
    }
}