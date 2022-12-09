class Motocicleta(marca: String, modelo: String, color: String, numeroPlazas: Int, tieneMaletero: Boolean) :
    Vehiculo(marca, modelo, color) {
    var numeroPlazas: Int = 0
    var tieneMaletero: Boolean = false

    init {
        this.numeroPlazas = numeroPlazas
        this.tieneMaletero = tieneMaletero
    }

    constructor(marca: String, modelo: String, color: String, numeroPlazas: Int)
            : this(marca, modelo, color, numeroPlazas, false) {

    }
}