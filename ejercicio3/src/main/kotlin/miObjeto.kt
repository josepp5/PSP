object miObjeto {
    var usuario: String = "Javier"
    var base_URL: String = "http://www.javiercarrasco.es/"

    fun funcionDeMiObjeto() {
        println("Has llamado a la función de un objeto.")
    }

    init {
        println("Iniciado por primera vez miObjeto")
        this.usuario = "Jose"
    }
}