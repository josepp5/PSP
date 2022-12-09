import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

public class Main {
    public static void main(String[] args) {


        // Como un almacen encuentra mas rapidamente el objeto y los objetos no estan ordenados
        HashMap<Integer,String> hash = new HashMap<>();
        hash.put(3,"asdfawefa");

        // Crear Lista y ArrayList
        List<String> array = new ArrayList<>();
        array.add("J");
        array.add("S");
        array.add("F");

        array.add(10, "J");

        // Recorrer LISTAS //

        // Primera Opcion
        for (String valor : array){
            System.out.println(valor);
        }
        // Segunda Opcion
        ListIterator<String> iterator = array.listIterator();
        while (iterator.hasNext()) {
            String valor = iterator.next();
        }

        // Tercera Opcion
        for (int i = 0; i < array.size(); i++) {
            String valor = array.get(i);
        }
    }
}