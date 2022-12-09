class Conductor(nombre: String, apellidos: String) : Persona(nombre, apellidos) {
    var permiso: String = ""
        get() {
            return "Permiso: $field"
        }
        set(value) {
            field = if (value.isEmpty()) "Sin permiso" else value
        }

    init {
        this.permiso = ""
    }

    constructor(nombre: String, apellidos: String, permiso: String)
            : this(nombre,apellidos){
                this.permiso = permiso
            }
}