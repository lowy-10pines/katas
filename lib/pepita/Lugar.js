class Lugar {
    constructor(km) {
        this.posicion = km
    }

    distanciaA(otroLugar) {
        const distanciaConSigno = this.posicion - otroLugar.posicion
        return distanciaConSigno * Math.sign(distanciaConSigno) 
    }

    Equals(object) {
        return (object.km != undefined) && this.km.Equals(object.km)
    }

    static BuenosAires() { return new Lugar(0) }
    static Rosario() { return new Lugar(300) }
    static Cordoba() { return new Lugar(800) }
    static Maschwitz() { return new Lugar(20) }
}


module.exports=Lugar
