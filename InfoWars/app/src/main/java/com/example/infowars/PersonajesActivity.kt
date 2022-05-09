package com.example.infowars


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_personajes.*


class PersonajesActivity : AppCompatActivity(), FragmentPersonajes.onItemClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personajes)

        val fragmento = FragmentPersonajes()

        //Para evitar el bug de letras más oscuras al rotar la pantalla
        if (savedInstanceState == null) {
            val fragment = FragmentPersonajes()
            this.supportFragmentManager.beginTransaction().add(R.id.lista_container, fragmento)
                .commit()
        }
    }

    override fun onItemClicked(personaje: Personaje) {
        if (detallesViewDisponible())
            mostarFramgmentDetalles(personaje.id)
        else
            iniciarDetallesActivity(personaje.id)
    }

    private fun mostarFramgmentDetalles(personajeId: String) {
        val detallesFragment = DetallesFragment.nuevaInstancia(personajeId)
        supportFragmentManager.beginTransaction().replace(R.id.detallesContainer, detallesFragment).commit()
    }

    private fun detallesViewDisponible() = detallesContainer != null


    /**
     * Metodo para que el botón nos lleve a la activity de detalles de un personaje
     * */
    private fun iniciarDetallesActivity(personajeId: String) {
        val intent: Intent = Intent(this, DetallesActivity::class.java)
        intent.putExtra("key_id", personajeId)
        startActivity(intent)
    }
}