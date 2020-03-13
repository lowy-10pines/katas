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


object Pepe {
    var categoria: Categoria = Gerente
    var bono: Bono = BonoDiezPorciento
    var faltas: Int = 0

    fun basico(): Double = categoria.basico()

    fun bono(): Double {
        return bono.bonoDesdeBasico(basico())
    }

    fun presentismo(): Int {
        return 100 - faltas * 5
    }

    fun sueldo(): Double {
        return basico() + bono() + presentismo()
    }
}

class PepeUnitTest {
    @Before
    fun setup() {
        Pepe.categoria = Gerente
        Pepe.bono = BonoFijo
    }
    @Test
    fun pepe_conCategoriaGerente_gana30000DeBasico() {
        Pepe.categoria = Gerente

        assertEquals(30000.0, Pepe.basico(),0.0)
    }

    @Test
    fun pepe_conCategoriaCadete_gana15000DeBasico() {
        Pepe.categoria = Cadete

        assertEquals(15000.0, Pepe.basico(),0.0)
    }

    @Test
    fun pepe_conBonoFijo_tiene80DeBono() {
        Pepe.bono = BonoFijo

        assertEquals(80.0, Pepe.bono(),0.0)
    }

    @Test
    fun pepe_conCategoriaGerenteYBono10porc_tiene_3000DeBono(){
        Pepe.bono = BonoDiezPorciento

        assertEquals(3000.0, Pepe.bono(),0.0)
    }

    @Test
    fun pepe_sinBono_tiene0DeBono() {
        Pepe.bono = BonoCero

        assertEquals(0.0, Pepe.bono(),0.0)
    }

    @Test
    fun pepe_con5Faltas_tiene75PorPresentismo(){
        Pepe.faltas = 5

        assertEquals(75, Pepe.presentismo())
    }

    @Test
    fun sueldoDePepe_GerenteBono10PorcientoCon3Faltas_cobra33085Pesos() {
        // Sueldo = 30000 basico gerente + 3000 bono10% + 85 presentismo
        Pepe.categoria = Gerente
        Pepe.bono = BonoDiezPorciento
        Pepe.faltas = 3

        assertEquals(33085.0, Pepe.sueldo(), 0.0)
    }
}