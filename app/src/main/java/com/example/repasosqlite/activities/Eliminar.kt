package com.example.repasosqlite.activities

import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView.AdapterContextMenuInfo
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SeekBar
import android.widget.TextView
import com.example.repasosqlite.R
import com.example.repasosqlite.controller.SqliteHelper
import com.example.repasosqlite.model.Persona
import com.example.repasosqlite.model.PersonaContract

class Eliminar : AppCompatActivity(), SeekBar.OnSeekBarChangeListener {
    private lateinit var txtProgreso:TextView
    private lateinit var seekEdad:SeekBar
    private lateinit var listaPersonas:ListView
    private lateinit var arraylistEdad : ArrayList<Persona>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eliminar)
        arraylistEdad=ArrayList<Persona>()

        txtProgreso=findViewById(R.id.txtProgreso)
        seekEdad=findViewById(R.id.seekEdad)
        listaPersonas=findViewById(R.id.listaPersonas)

        seekEdad.setOnSeekBarChangeListener(this)
        registerForContextMenu(listaPersonas)
    }

    fun onResume(edad:Int) {
        super.onResume()
        arraylistEdad.clear()
        val helper=SqliteHelper(this)
        val cursor: Cursor =helper.filtroEdad(edad)
        while(cursor.moveToNext()){
            arraylistEdad.add(
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
        val adaptador = ArrayAdapter(this, android.R.layout.simple_list_item_1, arraylistEdad)
        listaPersonas.adapter=adaptador



    }
    override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
        val progresoEdad=seekEdad.progress
        txtProgreso.setText(progresoEdad.toString())
        onResume(progresoEdad)

    }

    override fun onStartTrackingTouch(p0: SeekBar?) {

    }

    override fun onStopTrackingTouch(p0: SeekBar?) {

    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        menuInflater.inflate(R.menu.menu,menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterContextMenuInfo
        when(item.itemId){
            R.id.menuEliminar->{
                //Borramos la persona
                val persona = listaPersonas.adapter?.getItem(info.position) as Persona
                val helper=SqliteHelper(this)
                helper.eliminarPersona(persona)

            }
        }
        return super.onContextItemSelected(item)
    }

}