package com.example.infowars

import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject
import java.util.*


const val URL_PERSONAJES = "https://627d6a3b4268bf47ad4a9f58.mockapi.io/personajes"

object RepositorioPersonajes {

    private var personajes: MutableList<Personaje> = mutableListOf()

    fun peticionPersonajes(context: Context, exito: ((MutableList<Personaje>) -> Unit), error: (() -> Unit) ) {
        if (personajes.isEmpty()) {
            val peticion = JsonArrayRequest(Request.Method.GET, URL_PERSONAJES, null, { respuesta ->
                personajes = transformarPersonajes(respuesta)
                exito.invoke(personajes)
            }, { fallo ->
                error.invoke()

            })
            Volley.newRequestQueue(context).add(peticion)
        }else{
            exito.invoke(personajes)
        }
    }

    private fun transformarPersonajes(jsonArray: JSONArray): MutableList<Personaje>{
        val personajes = mutableListOf<Personaje>()
        for (i in 0..(jsonArray.length() - 1)) {
            val personaje = transformarPersonaje(jsonArray.getJSONObject(i))
            personajes.add(personaje)

        }
        return personajes
    }


    private fun transformarPersonaje(jsonPersonajes: JSONObject): Personaje{
        return Personaje(
            jsonPersonajes.getString("id"),
            jsonPersonajes.getString("nombre"),
            transformarFaccion(jsonPersonajes.getJSONObject("faccion")),
            jsonPersonajes.getString("planeta"),
            jsonPersonajes.getString("nave"),
            jsonPersonajes.getString("fIconica"),
            jsonPersonajes.getString("lado"),
            jsonPersonajes.getString("armas"),
            jsonPersonajes.getString("raza"),
            jsonPersonajes.getString("apariciones"),
            jsonPersonajes.getString("img"),

        )


    }

    private fun transformarFaccion (jsonFaccion: JSONObject): Faccion {
        return Faccion(
            jsonFaccion.getString("nombre"),
            jsonFaccion.getString("era"),
            //jsonFaccion.getString("año"),
            jsonFaccion.getString("img")
        )
    }


    fun findPersById(id: String?): Personaje? {
        return personajes.find { personaje -> personaje.id == id }
    }

}

