open class Vehiculo(marca: String, modelo: String, color: String) {
    var marca = ""
        set(value) {
            if (value.isEmpty()) {
                field = "Empty"
            } else
                field = value
        }
        get() {
            return if (field.isEmpty()) "Empty" else field
        }
    var modelo = ""
        set(value) {
            if (value.isEmpty()) {
                field = "Empty"
            } else
                field = value
        }
        get() {
            return if (field.isEmpty()) "Empty" else field
        }
    var color = ""
        set(value) {
            if (value.isEmpty()) {
                field = "Empty"
            } else
                field = value
        }
        get() {
            return if (field.isEmpty()) "Empty" else field
        }

    var matricula: Int = 0

    init {
        this.marca = marca
        this.modelo = modelo
        this.color = color
        numMatricula++
        this.matricula = numMatricula
    }

    companion object {
        var numMatricula: Int = 0

        init {
            println("Init Vehiculo Companion object")
        }
    }

}