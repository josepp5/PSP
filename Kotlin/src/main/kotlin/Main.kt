fun main(args: Array<String>) {
   var persona: Persona = Conductor("nombre", "apellido", "A")

   println(persona.printNombre())
}

fun Conductor.printNombre(){
   println("Hola")
}