const Lugar = require("./Lugar")

class Pepita {
    constructor() {
        this.energia = 45
        this.posicion = Lugar.BuenosAires()
    }

    comer() {
        this.energia += 4
    }

    volar(km) {
        this.energia -= this.costoDeVolar(km)
    }

    costoFijoPorVuelo() {
        return 10
    }

    irA(lugar) {
        const distancia = this.posicion.distanciaA(lugar)
        this.volar(distancia)
        this.posicion = lugar
    }

    puedeIrA(lugar) {
        const distancia = this.posicion.distanciaA(lugar)
        return this.energia >= this.costoDeVolar(distancia)
    }

    costoDeVolar(km) {
        return this.costoFijoPorVuelo() + km
    }
}

module.exports=Pepita
