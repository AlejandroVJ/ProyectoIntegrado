package com.example.infowars

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FragmentPersonajes : Fragment() {

    /**
     * Funcion lazy para organizar mi código y no tenerlo en el onCreate
     * */
    val lista: RecyclerView by lazy {
        val lista: RecyclerView = requireView().findViewById(R.id.lista)
        lista.layoutManager = LinearLayoutManager(context)

        lista
    }

    /**
     * Funcion lazy para organizar mi código y no tenerlo en el onCreate
     * */
    val adapter: RecyclerAdapter by lazy {
        val adapter = RecyclerAdapter { item, position ->
            clickListener.onItemClicked(item)
        }
        adapter
    }

    lateinit var clickListener: onItemClickListener

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is onItemClickListener)
            clickListener = context
        else
            throw IllegalAccessException("La actividad conectada no implementa OnItemClickListener")

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
      return inflater.inflate(R.layout.fragment_personajes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lista.adapter = adapter
        val personaje: MutableList<Personaje> = RepositorioPersonajes.personajes
        adapter.setPersonajes(personaje)
    }

    interface onItemClickListener {
        fun onItemClicked(personaje: Personaje)
    }
}