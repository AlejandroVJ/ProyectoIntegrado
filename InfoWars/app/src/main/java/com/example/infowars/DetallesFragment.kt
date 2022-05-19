package com.example.infowars

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detalles.*
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
                labelPlaneta.text = planeta.toString()
                labelFrase.text = fIconica
                labelNave.text = nave
                labelLado.text = lado
                labelArmas.text = armas
                labelRaza.text = raza
                labelApariciones.text = apariciones

                val personajesColor = Faccion.getColoresFaccion(personaje.faccion.nombre)
                imgView2.background =
                    context?.let { it1 -> ContextCompat.getDrawable(it1, personajesColor) }

                val colorView = Faccion.getColoresFaccionOpaco(personaje.faccion.nombre)
                btnFaccion.backgroundTintList =
                    context?.let { it1 -> ContextCompat.getColorStateList(it1, colorView) }

                val idIconos = Faccion.getIcono( personaje.faccion.nombre)
                val iconos = context?.let { it1 -> ContextCompat.getDrawable(it1, idIconos) }
                btnFaccion.setImageDrawable(iconos)

                Picasso.get()
                    .load(personaje.img)
                    .placeholder(R.drawable.prueba)
                    .into(imgPersonajeView)

            }


        }

        btnFaccion.setOnClickListener {
            if (personaje != null)
                mostrarDialog(personaje.faccion)
        }
    }
    private fun mostrarDialog(faccion: Faccion){
        val dialog = FaccionDialog.nuevaInstancia(faccion)
        dialog.show(childFragmentManager, "dialog_faccion")
    }


}