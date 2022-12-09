fun main(args: Array<String>) {
        val setOfItems = mutableSetOf<Vehiculo>()

        setOfItems.add(Vehiculo("Seat", "Panda", "Blanco"))
        setOfItems.add(Vehiculo("BMW", "Serie 1", "Negro"))
        setOfItems.add(Vehiculo("Yamaha", "MT1", "Azul"))
        setOfItems.add(Motocicleta("Honda", "CBR", "Repsol", 2, false))
        setOfItems.add(Motocicleta("Honda", "CBF", "Rojo", 2, true))
        setOfItems.add(Coche("Seat", "Panda", "Blanco", 4, 5))
        setOfItems.add(Coche("Seat", "Panda", "Blanco", 5, 5))

        setOfItems.forEach {
              print("Vehiculo ${it.matricula} ${it.marca} ${it.modelo} ${it.color}")

            when (it) {
                is Motocicleta -> println(" Motocicleta ${it.numeroPlazas} ${it.tieneMaletero}")
                is Coche -> println(" Coche  ${it.numeroPlazas} ${it.numeroPuertas}")
                is Vehiculo -> println(" Vehiculo ${it.marca} ${it.modelo} ${it.color}")
            }
        }

    var nombreUsuario = miObjeto.usuario
    var nombreUsuario2 = miObjeto.usuario
    var nombreUsuario3 = miObjeto.usuario
    var nombreUsuario4 = miObjeto.usuario
    var nombreUsuario5 = miObjeto.usuario

    println(nombreUsuario)
    println(nombreUsuario2)
    println(nombreUsuario3)
    println(nombreUsuario4)
    println(nombreUsuario5)

    miObjeto.funcionDeMiObjeto()


}