package com.example.repasosqlite.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.repasosqlite.R

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var btnInsertar:Button
    private lateinit var btnEliminar:Button
    private lateinit var btnDescartar: Button
    private lateinit var btnBuscar: Button
    private lateinit var btnMostrar : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    btnInsertar=findViewById(R.id.btnInsertar)
    btnDescartar=findViewById(R.id.btnDescartar)
    btnEliminar=findViewById(R.id.btnEliminar)
    btnBuscar=findViewById(R.id.btnBuscar)
    btnMostrar=findViewById(R.id.btnMostrar)

    btnInsertar.setOnClickListener(this)
    btnEliminar.setOnClickListener(this)
    btnDescartar.setOnClickListener(this)
    btnBuscar.setOnClickListener(this)
    btnMostrar.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btnInsertar->{
                var intent = Intent(this, Insertar::class.java)
                startActivity(intent)
            }
            R.id.btnEliminar->{
                var intent = Intent(this, Eliminar::class.java)
                startActivity(intent)
            }
            R.id.btnDescartar->{

            }
            R.id.btnBuscar->{

            }
            R.id.btnMostrar->{
                var intent = Intent(this, Mostrar::class.java)
                startActivity(intent)
            }
        }
    }
}