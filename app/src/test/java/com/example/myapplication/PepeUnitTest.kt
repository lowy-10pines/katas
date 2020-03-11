package com.example.myapplication

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

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

interface Categoria {
    fun basico(): Double
}
object Gerente: Categoria {
    override fun basico(): Double = 30000.0
}
object Cadete: Categoria {
    override fun basico(): Double = 15000.0
}


data class Empleado(var categoria: Categoria, var tipoDeBono: Bono) {
    var faltas: Int = 0

    fun basico(): Double = categoria.basico()

    fun bono(): Double {
        return tipoDeBono.bonoDesdeBasico(basico())
    }

    fun presentismo(): Int {
        return 100 - faltas * 5
    }

    fun sueldo(): Double {
        return basico() + bono() + presentismo()
    }
}

class PepeUnitTest {
    private lateinit var pepe: Empleado
    @Before
    fun setup() {
        pepe = Empleado(Gerente, BonoFijo)
    }
    @Test
    fun pepe_conCategoriaGerente_gana30000DeBasico() {
        pepe.categoria = Gerente

        assertEquals(30000.0, pepe.basico(),0.0)
    }

    @Test
    fun pepe_conCategoriaCadete_gana15000DeBasico() {
        pepe.categoria = Cadete

        assertEquals(15000.0, pepe.basico(),0.0)
    }

    @Test
    fun pepe_conBonoFijo_tiene80DeBono() {
        pepe.tipoDeBono = BonoFijo

        assertEquals(80.0, pepe.bono(),0.0)
    }

    @Test
    fun pepe_conCategoriaGerenteYBono10porc_tiene_3000DeBono(){
        pepe.tipoDeBono = BonoDiezPorciento

        assertEquals(3000.0, pepe.bono(),0.0)
    }

    @Test
    fun pepe_sinBono_tiene0DeBono() {
        pepe.tipoDeBono = BonoCero

        assertEquals(0.0, pepe.bono(),0.0)
    }

    @Test
    fun pepe_con5Faltas_tiene75PorPresentismo(){
        pepe.faltas = 5

        assertEquals(75, pepe.presentismo())
    }

    @Test
    fun sueldoDePepe_GerenteBono10PorcientoCon3Faltas_cobra33085Pesos() {
        // Sueldo = 30000 basico gerente + 3000 bono10% + 85 presentismo
        pepe.categoria = Gerente
        pepe.tipoDeBono = BonoDiezPorciento
        pepe.faltas = 3

        assertEquals(33085.0, pepe.sueldo(), 0.0)
    }
}