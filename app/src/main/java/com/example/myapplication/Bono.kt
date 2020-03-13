package com.example.myapplication

interface Bono {
    fun bonoDesdeBasico(base: Double): Double
}
object BonoFijo: Bono {
    override fun bonoDesdeBasico(base: Double): Double = 80.0
}
object BonoDiezPorciento: Bono {
    override fun bonoDesdeBasico(base: Double): Double = base * 0.1
}
object BonoCero: Bono {
    override fun bonoDesdeBasico(base: Double): Double = 0.0
}