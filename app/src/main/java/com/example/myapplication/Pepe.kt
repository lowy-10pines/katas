package com.example.myapplication

object Pepe {
    const val FALTAS_NECESARIAS_PARA_PERDER_PRESENTISMO = 20

    var categoria: Categoria = Gerente
    var bono: Bono = BonoDiezPorciento
    var faltas: Int = 0

    private fun basico(): Double = categoria.basico()

    private fun bono(): Double {
        return bono.bonoDesdeBasico(basico())
    }

    private fun presentismo(): Int {
        return 100 - faltas * 5
    }

    fun sueldo(): Double {
        return basico() + bono() + presentismo()
    }
}
