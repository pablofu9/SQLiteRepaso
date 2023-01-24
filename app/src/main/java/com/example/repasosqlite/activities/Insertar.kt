package com.example.repasosqlite.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.repasosqlite.R
import com.example.repasosqlite.controller.SqliteHelper
import com.example.repasosqlite.model.Persona

class Insertar : AppCompatActivity(), View.OnClickListener {
    private lateinit var edNumero:EditText
    private lateinit var edNombre:EditText
    private lateinit var edApellido:EditText
    private lateinit var edAltura:EditText
    private lateinit var edEdad:EditText

    private lateinit var btnInsertarPersona:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insertar)

        edNumero=findViewById(R.id.edNumero)
        edNombre=findViewById(R.id.edNombre)
        edApellido=findViewById(R.id.edApellido)
        edAltura=findViewById(R.id.edAltura)
        edEdad=findViewById(R.id.edEdad)

        btnInsertarPersona=findViewById(R.id.btnInsertarPersona)
        btnInsertarPersona.setOnClickListener(this)


    }

    override fun onClick(p0: View?) {
        val helper=SqliteHelper(this)
        if(edNumero.text.isNotEmpty() && edApellido.text.isNotEmpty() && edNombre.text.isNotEmpty() && edAltura.text.isNotEmpty() && edEdad.text.isNotEmpty()){
            val persona=Persona(edNumero.text.toString().toInt(), edNombre.text.toString(), edApellido.text.toString(), edAltura.text.toString().toInt(), edEdad.text.toString().toInt())
            helper.insertarPersona(persona)
            Toast.makeText(this, "Persona insertada", Toast.LENGTH_LONG).show()
            edNumero.setText("")
            edNombre.setText("")
            edApellido.setText("")
            edAltura.setText("")
            edEdad.setText("")
        }else{
            Toast.makeText(this, "Hay algun campo vacio", Toast.LENGTH_LONG).show()
        }

    }
}