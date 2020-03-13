describe("Pepita", () => {
    let pepita
    const Lugar = require("../../lib/pepita/Lugar")
    const Pepita = require("../../lib/pepita/Pepita")

    beforeEach( ()=> {
        pepita = new Pepita()
    })

    it("Tiene 45 joules de energía al nacer", () => { 
        expect(pepita.energia).toEqual(45)
    }) 

    it("Gana 4 joules al comer", () => {
        const energiaOriginal = pepita.energia

        pepita.comer()

        expect(pepita.energia).toEqual(energiaOriginal + 4)
    })

    it("Consume 1 joule por km volado + costo fijo", () => {
        const km = 5
        const energiaOriginal = pepita.energia

        pepita.volar(km)

        expect(pepita.energia).toEqual(energiaOriginal - pepita.costoDeVolar(km))
    })

    it("Nace en Buenos Aires", () => {
        expect(pepita.posicion).toEqual(Lugar.BuenosAires())
    })

    it("Gasta energía y cambiar su posición para ir a un lugar", () => {
        const energiaOriginal = pepita.energia
        var distancia = Lugar.Rosario().distanciaA(Lugar.BuenosAires())

        pepita.irA(Lugar.Rosario())
        
        expect(pepita.posicion).toEqual(Lugar.Rosario())
        expect(pepita.energia).toEqual(energiaOriginal - pepita.costoDeVolar(distancia))
    })

    it("Gasta la misma energía para volver a un lugar", () => {
        pepita.irA(Lugar.Rosario())
        const energiaOriginal = pepita.energia
        var distancia = Lugar.Rosario().distanciaA(Lugar.BuenosAires())

        pepita.irA(Lugar.BuenosAires())
        
        expect(pepita.energia).toEqual(energiaOriginal - pepita.costoDeVolar(distancia))
    })

    it("Puede ir a un lugar si tiene energía suficiente" , () => {
        expect(pepita.puedeIrA(Lugar.Maschwitz())).toBeTrue()
    }) 

    it("No puede ir a un lugar si no le alcanza la energía", () => {
        expect(pepita.puedeIrA(Lugar.Cordoba())).toBeFalse()
    })


    
})