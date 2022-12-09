import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        List<Student> list = new ArrayList<>();

        list.add(new Student("Jose", 19, Arrays.asList("PSP", "PDMD")));
        list.add(new Student("Manuel", 18, Arrays.asList("PSP", "Ingles")));
        list.add(new Student("Ramon", 17, Arrays.asList("PSP", "Matematicas")));
        list.add(new Student("Luis", 9, Arrays.asList("PDMD", "PDMD")));
        list.add(new Student("Fran", 20, Arrays.asList("PSP", "Ingles")));
        list.add(new Student("David", 9, Arrays.asList("PDMD", "Matematicas")));
        list.add(new Student("Laura", 21, Arrays.asList("PSP", "Ingles")));


        List<Student> listEdadFiltrada = listFilter(list, item -> item.getEdad() > 20);
        List<Student> listNombreFiltrado = listFilter(list, item -> item.getNombre().startsWith("J"));
        List<Student> listAsignaturaFiltrada = listFilter(list, item -> item.getAsignatura().contains("PSP"));

        System.out.println("Lista por Edad ////////////////////");
        for (Student item : listEdadFiltrada)
            System.out.println(item.getNombre() + " " + item.getEdad() + " " + item.getAsignatura());
        System.out.println("Lista por Nombre ////////////////////");
        for (Student item : listNombreFiltrado)
            System.out.println(item.getNombre() + " " + item.getEdad() + " " + item.getAsignatura());
        System.out.println("Lista por Asignaturas ////////////////////");
        for (Student item : listAsignaturaFiltrada)
            System.out.println(item.getNombre() + " " + item.getEdad() + " " + item.getAsignatura());

    }

    public static List<Student> listFilter(List<Student> arrayStudent, Predicate<Student> predicate){
        List<Student> list = new ArrayList<>();
        for (Student item : arrayStudent){
            if (predicate.test(item)) {
                list.add(item);
            }
        }
        return list;
    }
}
