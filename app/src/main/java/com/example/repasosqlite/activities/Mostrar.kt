package com.example.repasosqlite.activities

import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.repasosqlite.R
import com.example.repasosqlite.controller.SqliteHelper
import com.example.repasosqlite.model.Persona
import com.example.repasosqlite.model.PersonaContract

class Mostrar : AppCompatActivity() {

    private lateinit var lista : ListView
    private lateinit var arraylist : ArrayList<Persona>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar)

        //Sacar info en el listview, vamos a hacer uno simple
        arraylist= ArrayList<Persona>()
        lista=findViewById(R.id.lista)
        var helper = SqliteHelper(this)

        val cursor: Cursor=helper.leerPersona()
        while(cursor.moveToNext()){
            arraylist.add(
                Persona(
                    cursor.getInt(cursor.getColumnIndexOrThrow(PersonaContract.NUMERO)),
                    cursor.getString(cursor.getColumnIndexOrThrow(PersonaContract.NOMBRE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(PersonaContract.APELLIDO)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(PersonaContract.ALTURA)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(PersonaContract.EDAD)),

                )
            )
        }
        cursor.close()
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, arraylist)
        lista.adapter=adapter
    }
}