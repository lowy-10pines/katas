package com.example.myapplication

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class PepeUnitTest {
    @Before
    fun setup() {
        Pepe.categoria = Gerente
        Pepe.bono = BonoCero
        Pepe.faltas = Pepe.FALTAS_NECESARIAS_PARA_PERDER_PRESENTISMO
    }
    @Test
    fun pepe_conCategoriaGerente_gana30000DeBasico() {
        Pepe.categoria = Gerente

        assertEquals(30000.0, Pepe.sueldo(),0.0)
    }

    @Test
    fun pepe_conCategoriaCadete_gana15000DeBasico() {
        Pepe.categoria = Cadete

        assertEquals(15000.0, Pepe.sueldo(),0.0)
    }

    @Test
    fun pepe_conBonoFijo_tiene80DeBono() {
        val bono = BonoFijo.bonoDesdeBasico(0.0)

        assertEquals(80.0, bono ,0.0)
    }

    @Test
    fun pepe_conCategoriaGerenteYBono10porc_tiene_3000DeBono(){
        val basico = 30000.0
        val bono = BonoDiezPorciento.bonoDesdeBasico(basico)

        assertEquals(basico*0.1, bono,0.0)
    }

    @Test
    fun pepe_sinBono_tiene0DeBono() {
        val bono = BonoCero.bonoDesdeBasico(.0)

        assertEquals(0.0, bono,0.0)
    }

    @Test
    fun pepe_con5Faltas_tiene75PorPresentismo(){
        Pepe.faltas = 5

        assertEquals(30075.0, Pepe.sueldo(), 0.0)
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