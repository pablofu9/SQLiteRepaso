package com.example.repasosqlite.controller

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.repasosqlite.model.Persona
import com.example.repasosqlite.model.PersonaContract

class SqliteHelper(context:Context?) : SQLiteOpenHelper(context,NAME,null,VERSION) {
    companion object{
        private const val NAME="prueba.db"
        private const val VERSION=1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        //AÑADIMOS LA TABLA
        db?.execSQL(
            "CREATE TABLE "+
                    PersonaContract.TABLE_NAME + " ( "
                    +PersonaContract.NUMERO + " INTEGER PRIMARY KEY, "
                    +PersonaContract.NOMBRE + " TEXT, "
                    +PersonaContract.APELLIDO +" TEXT, "
                    +PersonaContract.ALTURA + " INTEGER, "
                    +PersonaContract.EDAD + " INTEGER);"
        )

        //AÑADIMOS VALORES PREDETERMINADOS A LA TABLA
        db?.execSQL(
            "INSERT INTO " + PersonaContract.TABLE_NAME + " (numero,nombre,apellido,altura,edad) VALUES" +
                    "(1, 'Pedro', 'Lopez', 178, 22)," +
                    "(2, 'Maria', 'Perez', 150, 33)," +
                    "(3, 'Juan', 'Agundez', 190, 18)," +
                    "(4, 'Marta', 'Viña', 163, 26);"

        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {

    }
    //METODO PARA SACAR LA PERSONA ENTERA
    fun leerPersona(): Cursor{
        val db = readableDatabase
        val sql = "select * from "+PersonaContract.TABLE_NAME
        return db.rawQuery(sql,null)
    }
    //METODO PARA INSERTAR UNA PERSONA
    fun insertarPersona(persona: Persona): Long{
        val db = writableDatabase
        val values = ContentValues()
        values.put(PersonaContract.NUMERO, persona.numero)
        values.put(PersonaContract.NOMBRE, persona.nombre)
        values.put(PersonaContract.APELLIDO, persona.apellido)
        values.put(PersonaContract.ALTURA, persona.altura)
        values.put(PersonaContract.EDAD, persona.edad)

        return db.insert(PersonaContract.TABLE_NAME, null, values)
    }

    //METODO PARA FILTRAR EDAD

    fun filtroEdad(edad:Int):Cursor{
        val db = readableDatabase
        val sql = "select * from "+PersonaContract.TABLE_NAME+ " where edad< "+edad
        return db.rawQuery(sql,null)
    }
     fun eliminarPersona(persona:Persona){
        val db = writableDatabase
        db.execSQL("delete from "+PersonaContract.TABLE_NAME+" where "+PersonaContract.NUMERO+" ='"+persona.numero+"'")
     }
}