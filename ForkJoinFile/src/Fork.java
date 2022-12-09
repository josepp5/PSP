import java.util.ArrayList;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;


public class Fork extends RecursiveTask<ArrayList<String>> {
    public static final int LINEAS = 10;
    ArrayList<String> list;
    /* First index of the list to search */
    int first;
    /* Last index of the list to search */
    int last;
    /* Text to be searched in the list */
    String text;
    public Fork(ArrayList<String> list, String text, int first, int last) {
        this.list = list;
        this.text = text;
        this.first = first;
        this.last = last;
    }
    @Override
    protected ArrayList<String> compute() {
        ArrayList<String> results = new ArrayList<String>();
        if (last - first <= LINEAS)
            results = search();
        else {
            int middle = (first + last) / 2;
            System.out.println("Creating 2 subtasks...");
            Fork s1 = new Fork(list, text, first, middle + 1);
            Fork s2 = new Fork(list, text, middle + 1, last);
            invokeAll(s1, s2);
            try {
                results = s1.get();
                ArrayList<String> aux = s2.get();
                results.addAll(aux);
            } catch (Exception e) { }
        }
        return results;
    }
    public ArrayList<String> search() {
        ArrayList<String> results = new ArrayList<String>();
        for (int i = first; i < last; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) { }
            if (list.get(i).contains(text))
                results.add(list.get(i).replaceAll(text,"Java"));
            else results.add(list.get(i));
        }
        return results;
    }
}

