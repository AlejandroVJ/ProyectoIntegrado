package com.example.infowars

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.squareup.picasso.Picasso
import java.io.Serializable
import kotlinx.android.synthetic.main.dialog_faccion.view.*

class FaccionDialog: DialogFragment() {

    companion object{
        fun nuevaInstancia(faccion: Faccion): FaccionDialog{
            val argumento = Bundle()
            argumento.putSerializable("key_faccion", faccion as Serializable)
            val dialog = FaccionDialog()
            dialog.arguments = argumento
            return dialog
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_faccion, null, false)
        val faccion = arguments?.getSerializable("key_faccion") as Faccion
        with(faccion) {
            dialogView.labelNombre.text = nombre
            dialogView.labelEra.text = era
            dialogView.layoutDialog.background =
                context?.let { ContextCompat.getDrawable(it, Faccion.getColoresFaccionOpaco(nombre)) }
        }

        Picasso.get().load(faccion.img).into(dialogView.imgFaccion)


        return  AlertDialog.Builder(context).setView(dialogView)
            .setPositiveButton(R.string.label_aceptar, { _,_ -> dismiss() }).create()

    }
}