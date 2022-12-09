open class Persona(nombre: String, apellidos: String) {
    var nombre: String = ""
        get() {
            return if (field.isEmpty()) "Sin nombre" else field
        }
        set(value) {
            field = if (value.isEmpty()) "Sin nombre" else value
        }

    var apellido: String = ""

    init {
        this.nombre = nombre
        this.apellido = apellido
    }

    fun printNombre() = "Mi nombre es $nombre $apellido"
    }
