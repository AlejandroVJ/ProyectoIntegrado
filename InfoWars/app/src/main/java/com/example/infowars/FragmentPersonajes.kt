package com.example.infowars

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_personajes.*

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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_personajes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lista.adapter = adapter
        btnReintentar.setOnClickListener {
            reintentar()
        }
    }

    override fun onResume() {
        super.onResume()
        peticionPersonajes()
    }

    private fun reintentar() {
        errorLayout.visibility = View.INVISIBLE
        progressBar.visibility = View.VISIBLE

        peticionPersonajes()
    }

    private fun peticionPersonajes() {
        context?.let {
            RepositorioPersonajes.peticionPersonajes(it,
                { personajes ->
                    view?.let {
                    progressBar.visibility = View.INVISIBLE
                    lista.visibility = View.VISIBLE
                    adapter.setPersonajes(personajes)
                }
                },
                    {
                        view?.let {
                        progressBar.visibility = View.INVISIBLE
                        errorLayout.visibility = View.VISIBLE
                    }
                })
        }
    }

    interface onItemClickListener {
        fun onItemClicked(personaje: Personaje)
    }
}