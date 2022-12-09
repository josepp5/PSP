import java.io.BufferedReader;
import java.io.FileReader;

public class Main {
    public static void main(String[] args) {

        try {
            validate("egsgsrg", "23525");
            System.out.println("Login succes");
        } catch (ArithmeticException ex) {
            System.out.println("Hello ArithmeticException!");
        } catch (ValidateException ex){
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Hello Exception!");
        }
    }

    public static void validate(String nombre, String password) throws ValidateException {
        if (nombre != "jose"){
            throw new ValidateException("Nombre invalid");
        } else if (password != "1234"){
            throw new ValidateException("Pass invalid");
        }
    }
}