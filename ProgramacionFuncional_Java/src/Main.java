import java.io.File;

public class Main {
    public static void main(String[] args) {
        File dir = new File("./");
        File[] javaFiles = dir.listFiles(new JavaFileFilter());
        for (File file : javaFile){
            System.out.println(file.getName());
        }
    }
}