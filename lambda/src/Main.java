import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Student> list = new ArrayList<>();

        list.add(new Student("Jose", 19));
        list.add(new Student("Manuel", 18));
        list.add(new Student("Ramon", 17));
        list.add(new Student("Juis", 9));
        list.add(new Student("Fran", 20));
        list.add(new Student("David", 9));
        list.add(new Student("Laura", 21));

        //////////////       ASI NO!!!     ///////////////////////
    /*
        List<Student> listTenAge = filterAgeTen(list);
        for (Student item : listTenAge) {
            System.out.println(item.getName() + " " + item.getAge());
        }

        List<Student> listJName = filterNameJ(list);

        for (Student item : listJName) {
            System.out.println(item.getName() + " " + item.getAge());
        }
    */

        ///////     FORMA CORRECTA Con LAMBDA  V  ////////////////////
        List<Student> listAgeFilter = filter(list, item -> item.getAge() > 10);
        

        ///////     FORMA CORRECTA SIN LAMBDA      ///////////////////////
        List<Student> listNameFilter = filter(list, new Condition() {
            @Override
            public boolean accept(Student item) {
                return item.getName().startsWith("J");
            }
        });
    }

    public static List<Student> filter(List<Student> array, Condition condition){
        List<Student> list = new ArrayList<>();

        for (Student item : array) {
            if (condition.accept(item)) {
                list.add(item);
            }
        }
        return list;
    }
    /////////////////     FORMA CORRECTA HASTA AQUI  ^  /////////////////




    ////////////// FORMA NO EFICIENTE  V     //////////////////7

    public static List<Student> filterNameJ(List<Student> array){
        List<Student> list = new ArrayList<>();

        for (Student item : array) {
            if (item.getName().startsWith("J")){
                list.add(item);
            }
        }
        return list;
    }

    public static List<Student> filterAgeTen(List<Student> array){
        List<Student> list = new ArrayList<>();

        for (Student item : array) {
            if (item.getAge() > 10) {
                list.add(item);
            }
        }
        return list;
    }
}