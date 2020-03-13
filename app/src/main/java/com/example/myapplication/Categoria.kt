package com.example.myapplication

interface Categoria {
    fun basico(): Double
}
object Gerente: Categoria {
    override fun basico(): Double = 30000.0
}
object Cadete: Categoria {
    override fun basico(): Double = 15000.0
}
