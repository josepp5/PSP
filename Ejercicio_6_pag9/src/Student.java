import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Student {
    public int edad;
    public String Nombre;

    public List<String> asignatura;

    public Student(String nombre, int edad, List<String> asignatura) {
        this.edad = edad;
        Nombre = nombre;
        this.asignatura = asignatura;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public List<String> getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(List<String> asignatura) {
        this.asignatura = asignatura;
    }
}
