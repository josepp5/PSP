import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader  br = new BufferedReader(new FileReader("texto.txt"));
        ArrayList<String> list = new ArrayList<String>();

        while (br.ready() != false) list.add(br.readLine());
        br.close();
        Fork v = new Fork(list, "java", 0, list.size());
        ForkJoinPool pool = new ForkJoinPool();
        pool.execute(v);
        do {
            try {
                Thread.sleep(100);
            } catch (Exception e) { }
        } while (!v.isDone());

        try {
            ArrayList<String> results = v.get();
            FileWriter fr = new FileWriter("texto.txt");
            for (int i = 0; i < results.size(); i++){
                fr.write(results.get(i) + "\n");
            }
            fr.close();
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
        pool.shutdown();
    }
}