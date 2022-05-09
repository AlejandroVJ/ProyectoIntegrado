package com.example.infowars

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_detalles.*
import kotlinx.android.synthetic.main.fragment_detalles.*
import kotlinx.android.synthetic.main.fragment_detalles.button
import kotlinx.android.synthetic.main.fragment_detalles.labelArmas
import kotlinx.android.synthetic.main.fragment_detalles.labelFrase
import kotlinx.android.synthetic.main.fragment_detalles.labelLado
import kotlinx.android.synthetic.main.fragment_detalles.labelNave
import kotlinx.android.synthetic.main.fragment_detalles.labelNombre
import kotlinx.android.synthetic.main.fragment_detalles.labelPlaneta

class DetallesFragment : Fragment()  {


    companion object {
        fun nuevaInstancia(id: String?): DetallesFragment {
            val instancia = DetallesFragment()
            val args = Bundle()
            args.putString("key_id", id)

            instancia.arguments = args

            return instancia
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detalles, container, false )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = arguments?.getString("key_id")
        val personaje = RepositorioPersonajes.findPersById(id)

        personaje?.let {
            with(personaje) {
                labelNombre.text = nombre
                labelPlaneta.text = planeta
                labelFrase.text = fIconica
                labelNave.text = nave
                labelLado.text = lado
                labelArmas.text = armas
                button.text = faccion.nombre
            }

        }

        button.setOnClickListener {
            Toast.makeText(context, personaje?.faccion?.nombre, Toast.LENGTH_SHORT).show()
        }

    }


}