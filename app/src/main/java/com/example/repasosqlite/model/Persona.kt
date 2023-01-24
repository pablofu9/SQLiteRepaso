package com.example.repasosqlite.model

class Persona {
    var numero:Int
    var nombre:String
    var apellido:String
    var altura:Int
    var edad:Int

    constructor(numero: Int, nombre: String, apellido: String, altura: Int, edad: Int) {
        this.numero = numero
        this.nombre = nombre
        this.apellido = apellido
        this.altura = altura
        this.edad = edad
    }

    constructor(nombre: String, apellido: String, altura: Int, edad: Int) {
        this.numero=0
        this.nombre = nombre
        this.apellido = apellido
        this.altura = altura
        this.edad = edad
    }
    constructor(){
        numero=0
        nombre=""
        apellido=""
        altura=0
        edad=0
    }
    override fun toString(): String {
        return "Persona(numero=$numero, nombre='$nombre', apellido='$apellido', altura=$altura, edad=$edad)"
    }


}