import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {

        //// LEE UN FICHERO Y LO COPIA EN OTRO

        PrintWriter print = null;
        BufferedReader buffer = null;
        try {
            buffer = new BufferedReader(new FileReader("text.txt"));
            print = new PrintWriter(new FileWriter("in.txt"));
            String line;
            while ((line = buffer.readLine()) != null) {
                print.println(line);
            }
        } catch (FileNotFoundException e) {
            System.err.println("File /home/pepe/file.txt doesn't exist!");
        } catch (IOException e) {
            System.err.println("Error reading /home/pepe/file.txt.");
        } finally {
            if (buffer != null){
                buffer.close();
            }
            if (print != null){
                print.close();
            }
        }


            //// FILE READER
        /*
        try(BufferedReader buffer = new BufferedReader(new FileReader("text .txt"))) {
            String line;
            while((line = buffer.readLine()) != null) {
                System.out.println(line);
            }
        } catch (
                FileNotFoundException e) {
            System.err.println("File /home/pepe/file.txt doesn't exist!");
        } catch (
                IOException e) {
            System.err.println("Error reading /home/pepe/file.txt.");
        }

        ///// FILE WRITER

        try (PrintWriter print = new PrintWriter(new FileWriter("/home/pepe/file.txt"))) {
            print.println("Hello world!");
        } catch (IOException e) {
            System.err.println("Error writing: " + e.getMessage());
        }*/


    }
}