package com.example.infowars

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


class DetallesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles)

        val id = intent.getStringExtra("key_id")

        if (savedInstanceState == null ){
            val fragment = DetallesFragment.nuevaInstancia(id)
            supportFragmentManager.beginTransaction().add(R.id.detallesContainer, fragment).commit()
         }
    }
}