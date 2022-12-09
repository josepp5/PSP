class Coche(marca: String, modelo: String, color: String, numeroPuertas: Int, numeroPlazas: Int) :
    Vehiculo(marca, modelo, color) {
    var numeroPuertas: Int = 0
    var numeroPlazas: Int = 0

    init {
        this.numeroPlazas = numeroPlazas
        this.numeroPuertas = numeroPuertas

    }
}
