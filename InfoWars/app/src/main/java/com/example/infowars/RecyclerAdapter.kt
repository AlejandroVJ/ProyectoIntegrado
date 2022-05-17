package com.example.infowars

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_personaje.view.*

class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.viewHolder> {

    constructor() : super() {
        itemClickListener = null
    }

    constructor(itemClickListener: ((Personaje, Int) -> Unit)) : super() {
        this.itemClickListener = itemClickListener
    }

    private val items: MutableList<Personaje> = mutableListOf()

    private val itemClickListener: ((Personaje, Int) -> Unit)?

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_personaje, parent, false)
        return viewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val item = items[position]
        holder.personaje = item
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setPersonajes(personaje: MutableList<Personaje>) {
        items.clear()
        items.addAll(personaje)
        notifyDataSetChanged()
    }

    inner class viewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //? = a puede ser o no nulo
        var personaje: Personaje? = null
            set(value) {
                value?.let {
                    itemView.labelNombre.text = value.nombre
                    itemView.labelFrase.text = value.fIconica
                    val colorFaccion = Faccion.getColoresFaccion(value.faccion.nombre)
                    itemView.imgPrueba.background = ContextCompat.getDrawable(itemView.context, colorFaccion)

                    Picasso.get()
                        .load(value.img)
                        .placeholder(R.drawable.prueba)
                        .into(itemView.imgPersonaje)


                }
                field = value
            }

        init {
           itemView.setOnClickListener {
                personaje?.let{
                    itemClickListener?.invoke(personaje as Personaje, adapterPosition)
                }
            }
        }


    }
}